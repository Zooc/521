package an.devhp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import an.devhp.base.BaseActivity;
import an.devhp.util.DeviceUtil;
import an.devhp.util.JsonUtil;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-06-23 00:35
 * @version: 1.0
 */

public class AnMainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_an_main);
        TextView deviceInfoTv = (TextView) findViewById(R.id.cpu_info_tv);
        deviceInfoTv.setText(JsonUtil.prettyFormatJson(DeviceUtil.getMobileInfo()));
        deviceInfoTv.setText(DeviceUtil.getCpuType());
        deviceInfoTv.setText(DeviceUtil.getIpAddress(this));
        deviceInfoTv.setText(DeviceUtil.getCpuModel());
    }
}
