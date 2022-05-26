class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        
        char[] chars = strs[0].toCharArray();
        int i = 0;
        boolean flag = true;
        for (; i < chars.length; ++i) {
            char ch = chars[i];
            
            for (int j = 1; j < strs.length; ++j) {
                if (strs[j].length() <= i) {
                    flag = false;
                    break;
                }
                if (strs[j].charAt(i) != ch) {
                    flag = false;
                    break;
                }
                
            }
            if (!flag) {
                break;
            }
        }
        return strs[0].substring(0, i);
        
        
    }
}