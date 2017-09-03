package an.devhp.ui.listener;

import java.util.List;

/**
 * @description:
 * @author: Kenny
 * @date: 2017-09-03 11:13
 * @version: 1.0
 */

public interface ListItemClickListener {

    <T> void onListItemClick(List<T> dataList, int position);
}
