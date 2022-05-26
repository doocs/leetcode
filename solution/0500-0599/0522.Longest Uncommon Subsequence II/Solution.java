class Solution {
    public int findLUSlength(String[] strs) {
        int res = -1;
        if (strs == null || strs.length == 0) {
            return res;
        }
        if (strs.length == 1) {
            return strs[0].length();
        }
        // 两两比较
        // 1、存在子串,直接不比较后面的字符串
        // 2、不存在子串,判断当前字符串是否是最长的字符串
        for (int i = 0, j; i < strs.length; i++) {
            for (j = 0; j < strs.length; j++) {
                if (i == j) {
                    continue;
                }
                // 根据题意，子串 可以 不是 原字符串中 连续的子字符串
                if (isSubsequence(strs[i], strs[j])) {
                    break;
                }
            }
            if (j == strs.length) {
                res = Math.max(res, strs[i].length());
            }
        }
        return res;
    }

    public boolean isSubsequence(String x, String y) {
        int j = 0;
        for (int i = 0; i < y.length() && j < x.length(); i++) {
            if (x.charAt(j) == y.charAt(i))
                j++;
        }
        return j == x.length();
    }
}
