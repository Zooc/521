package an.devhp.util;

import java.util.Collection;
import java.util.List;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-03 13:40
 * @version: 1.0
 */

public class LsUtil {

    private LsUtil() {
        //no instance
    }

    /**
     * 校验集合是否为空
     *
     * @param c
     * @return
     */
    public static boolean isLsEmpty(Collection c) {
        return c != null && c.size() > 0;
    }

    /**
     * 获取列表元素
     *
     * @param list
     * @param index
     * @param <T>
     * @return
     */
    public static <T> T getLsElement(List<T> list, int index) {
        if (list != null && index < list.size()) {
            return list.get(index);
        }
        return null;
    }

    /**
     * 为列表添加元素
     *
     * @param list
     * @param ts
     * @param shouldClearList 是否清空集合
     * @param <T>
     */
    public static <T> void add(List<T> list, boolean shouldClearList, T... ts) {
        if (list != null) {
            if (shouldClearList) {
                list.clear();
            }
            if (ts != null) {
                for (T t : ts) {
                    list.add(t);
                }
            }
        }
    }

    /**
     * 为列表添加元素
     *
     * @param list
     * @param ts
     * @param <T>
     */
    public static <T> void add(List<T> list, T... ts) {
        add(list, false, ts);
    }

}
