class Solution {
    public int secondsToRemoveOccurrences(String s) {
        char[] cs = s.toCharArray();
        boolean find = true;
        int ans = 0;
        while (find) {
            find = false;
            for (int i = 0; i < cs.length - 1; ++i) {
                if (cs[i] == '0' && cs[i + 1] == '1') {
                    char t = cs[i];
                    cs[i] = cs[i + 1];
                    cs[i + 1] = t;
                    ++i;
                    find = true;
                }
            }
            if (find) {
                ++ans;
            }
        }
        return ans;
    }
}