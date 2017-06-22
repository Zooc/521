package an.devhp.util;

import android.content.Context;
import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-06-23 01:05
 * @version: 1.0
 */

public class DeviceUtil {
    private DeviceUtil() {
        //no instance
    }
//http://chriszeng87.iteye.com/blog/1920647?utm_source=tuicool&utm_medium=referral
    //    adb命令查看cpu info
    //    1. adb shell
    //    2. cat /proc/cpuinfo

    /**
     * 通过反射获取手机Build信息
     *
     * @return
     */
    public static String getMobileInfo() {
        JSONObject mbInfo = new JSONObject();
        try {
            Field[] fields = Build.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                String value = field.get(null).toString();
                mbInfo.put(name, value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mbInfo.toString();
    }

    /**
     * 获取cpu信息的字符串
     *
     * @return
     */
    public static String getCpuInfo() {
//        if (Build.CPU_ABI.equals("x86")) {
//            return "Intel";
//        }

        String strInfo = "";
        byte[] bs = new byte[1024];
        try {
            RandomAccessFile reader = new RandomAccessFile("proc/cpuinfo", "r");
            reader.read(bs);
            String ret = new String(bs);
            int index = ret.indexOf(0);
            if (index != -1) {
                strInfo = ret.substring(0, index);
            } else {
                strInfo = ret;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strInfo;
    }

    /**
     * 获取cpu类型
     *
     * @return
     */
    public static String getCpuType() {
        String cpuInfo = getCpuInfo();
        String cpuType = null;

        if (cpuInfo.contains("ARMv5")) {
            cpuType = "armv5";
        } else if (cpuInfo.contains("ARMv6")) {
            cpuType = "armv6";
        } else if (cpuInfo.contains("ARMv7")) {
            cpuType = "armv7";
        } else if (cpuInfo.contains("Intel")) {
            cpuType = "x86";
        } else {
            cpuType = "unknown";
            return cpuType;
        }

        if (cpuInfo.contains("neon")) {
            cpuType += "_neon";
        } else if (cpuInfo.contains("vfpv3")) {
            cpuType += "_vfpv3";
        } else if (cpuInfo.contains(" vfp")) {
            cpuType += "_vfp";
        } else {
            cpuType += "_none";
        }

        return cpuType;

    }

    /**
     * @return
     * @hide
     */
    public static CPUInfo getCPUInfo() {
        String strInfo = null;
        try {
            byte[] bs = new byte[1024];
            RandomAccessFile reader = new RandomAccessFile("/proc/cpuinfo", "r");
            reader.read(bs);
            String ret = new String(bs);
            int index = ret.indexOf(0);
            if (index != -1) {
                strInfo = ret.substring(0, index);
            } else {
                strInfo = ret;
            }
        } catch (IOException ex) {
            strInfo = "";
            ex.printStackTrace();
        }

        CPUInfo info = parseCPUInfo(strInfo);
        info.mCPUMaxFreq = getMaxCpuFreq();

        return info;
    }

    private final static String kCpuInfoMaxFreqFilePath = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";

    private static int getMaxCpuFreq() {
        int result = 0;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(kCpuInfoMaxFreqFilePath);
            br = new BufferedReader(fr);
            String text = br.readLine();
            if (text != null) {
                result = Integer.parseInt(text.trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null)
                try {
                    fr.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

        return result;
    }


    public static class CPUInfo {
        public CPUInfo() {

        }

        public static final int CPU_TYPE_UNKNOWN = 0x00000000;
        public static final int CPU_TYPE_ARMV5TE = 0x00000001;
        public static final int CPU_TYPE_ARMV6 = 0x00000010;
        public static final int CPU_TYPE_ARMV7 = 0x00000100;
        public static final int CPU_TYPE_X86 = 0x00001000;

        public static final int CPU_FEATURE_UNKNOWS = 0x00000000;
        public static final int CPU_FEATURE_VFP = 0x00000001;
        public static final int CPU_FEATURE_VFPV3 = 0x00000010;
        public static final int CPU_FEATURE_NEON = 0x00000100;

        public static final String CPU_STR_TYPE_UNKNOWN = "unknown";
        public static final String CPU_STR_TYPE_ARMV5TE = "ARMv5";
        public static final String CPU_STR_TYPE_ARMV6 = "ARMv6";
        public static final String CPU_STR_TYPE_ARMV7 = "ARMv7";

        public static final String CPU_STR_FEATURE_UNKNOWS = "_none";
        public static final String CPU_STR_FEATURE_VFP = "vfp";
        public static final String CPU_STR_FEATURE_VFPV3 = "vfpv3";
        public static final String CPU_STR_FEATURE_NEON = "neon";

        public int mCPUType;
        public int mCPUCount;
        public int mCPUFeature;
        public double mBogoMips;
        public long mCPUMaxFreq;
    }

    private static CPUInfo parseCPUInfo(String cpuInfo) {
        if (cpuInfo == null || "".equals(cpuInfo)) {
            return null;
        }

        CPUInfo ci = new CPUInfo();
        ci.mCPUType = CPUInfo.CPU_TYPE_UNKNOWN;
        ci.mCPUFeature = CPUInfo.CPU_FEATURE_UNKNOWS;
        ci.mCPUCount = 1;
        ci.mBogoMips = 0;

        if (cpuInfo.contains("ARMv5")) {
            ci.mCPUType = CPUInfo.CPU_TYPE_ARMV5TE;
        } else if (cpuInfo.contains("ARMv6")) {
            ci.mCPUType = CPUInfo.CPU_TYPE_ARMV6;
        } else if (cpuInfo.contains("ARMv7")) {
            ci.mCPUType = CPUInfo.CPU_TYPE_ARMV7;
        } else if (cpuInfo.contains("Intel")) {
            ci.mCPUType = CPUInfo.CPU_TYPE_X86;
        }

        if (cpuInfo.contains("neon")) {
            ci.mCPUFeature |= CPUInfo.CPU_FEATURE_NEON;
        }

        if (cpuInfo.contains("vfpv3")) {
            ci.mCPUFeature |= CPUInfo.CPU_FEATURE_VFPV3;
        }

        if (cpuInfo.contains(" vfp")) {
            ci.mCPUFeature |= CPUInfo.CPU_FEATURE_VFP;
        }

        String[] items = cpuInfo.split("\n");
        for (String item : items) {
            if (item.contains("CPU variant")) {
                int index = item.indexOf(": ");
                if (index >= 0) {
                    String value = item.substring(index + 2);
                    try {
                        ci.mCPUCount = Integer.decode(value);
                        ci.mCPUCount = ci.mCPUCount == 0 ? 1 : ci.mCPUCount;
                    } catch (NumberFormatException e) {
                        ci.mCPUCount = 1;
                    }
                }
            } else if (item.contains("BogoMIPS")) {
                int index = item.indexOf(": ");
                if (index >= 0) {
                    String value = item.substring(index + 2);
                }
            }
        }

        return ci;
    }

    /**
     * 获取设备内存大小值
     *
     * @return 内存大小, 单位MB
     */
    public static long getTotalMemory() {
        String str1 = "/proc/meminfo";
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();
            if (str2 != null) {
                arrayOfString = str2.split("\\s+");
                initial_memory = Integer.valueOf(arrayOfString[1]).intValue() / 1024;
            }
            localBufferedReader.close();
            return initial_memory;
        } catch (IOException e) {
            return -1;
        }
    }

    /**
     * 获取android CPU类型
     *
     * @return String CPU类型
     */
    public static String getCpuModel() {
        String cpu_model = "";

        CPUInfo in = getCPUInfo();

        if ((in.mCPUType & CPUInfo.CPU_TYPE_ARMV5TE) == CPUInfo.CPU_TYPE_ARMV5TE) {
            cpu_model = "armv5";
        } else if ((in.mCPUType & CPUInfo.CPU_TYPE_ARMV6) == CPUInfo.CPU_TYPE_ARMV6) {
            cpu_model = "armv6";
        } else if ((in.mCPUType & CPUInfo.CPU_TYPE_ARMV7) == CPUInfo.CPU_TYPE_ARMV7) {
            cpu_model = "armv7";
        } else if ((in.mCPUType & CPUInfo.CPU_TYPE_X86) == CPUInfo.CPU_TYPE_X86) {
            cpu_model = "x86";
        } else {
            cpu_model = "unknown";
        }
        return cpu_model;
    }

    /**
     * 获取android CPU特性
     *
     * @return String CPU特性
     */
    public static String getCpuFeature() {
        String cpu_feature = "";

        CPUInfo in = getCPUInfo();

        if ((in.mCPUFeature & CPUInfo.CPU_FEATURE_NEON) == CPUInfo.CPU_FEATURE_NEON)
            cpu_feature = "neon";
        else if ((in.mCPUFeature & CPUInfo.CPU_FEATURE_VFP) == CPUInfo.CPU_FEATURE_VFP)
            cpu_feature = "vfp";
        else if ((in.mCPUFeature & CPUInfo.CPU_FEATURE_VFPV3) == CPUInfo.CPU_FEATURE_VFPV3)
            cpu_feature = "vfpv3";
        else
            cpu_feature = "unknown";
        return cpu_feature;
    }

    /**
     * 获取ip地址
     *
     * @param mContext Context
     * @return ip地址字符串
     */
    public static String getIpAddress(Context mContext) {
        String ipAddress = null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ipAddress = inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            return null;
        }
        return ipAddress;
    }


}
