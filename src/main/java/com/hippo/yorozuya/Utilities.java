/*
 * Copyright 2015 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.yorozuya;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.regex.Pattern;

public final class Utilities {

    /**
     * Whether the array contain the element
     *
     * @param array the array
     * @param obj the element
     * @return true for the array contain the element
     */
    public static boolean contain(@NonNull Object[] array, Object obj) {
        for (Object o : array) {
            if (o.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public static String getNameFromUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            int fragment = url.lastIndexOf('#');
            if (fragment > 0) {
                url = url.substring(0, fragment);
            }

            int query = url.lastIndexOf('?');
            if (query > 0) {
                url = url.substring(0, query);
            }

            int filenamePos = url.lastIndexOf('/');
            String filename =
                    0 <= filenamePos ? url.substring(filenamePos + 1) : url;

            // if the filename contains special characters, we don't
            // consider it valid for our matching purposes:
            if (!filename.isEmpty() &&
                    Pattern.matches("[a-zA-Z_0-9\\.\\-\\(\\)\\%]+", filename)) {
                int dotPos = filename.lastIndexOf('.');
                if (0 < dotPos) {
                    return filename.substring(0, dotPos);
                }
            }
        }

        return null;
    }
}
