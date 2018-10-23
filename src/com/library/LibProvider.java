package com.library;

import com.library.util.OsUtil;
import com.library.util.PathUtil;
import com.std.framework.annotation.RouterModule;
import com.std.framework.annotation.RouterPath;

/**
 * Description:
 * Author: lixiao
 * Create on: 2018/10/17.
 * Job number:
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
@RouterModule(schema = "chunyu", host = "lib")
public class LibProvider {

    @RouterPath(value = "/getApiLevel")
    public int getApiLevel() {
        return OsUtil.getApiLevel();
    }

    @RouterPath(value = "/mergePath")
    public String mergePath(String path1, String path2) {
        return PathUtil.merge(path1, path2);
    }
}
