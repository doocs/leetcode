class Solution {
    public boolean isAnagram(String s, String t) {
        int[] map = new int[26];
        for (int i = s.length() - 1; i >= 0; i --) {
            map[(int)s.charAt(i) - 'a'] ++;
        }
        for (int i = t.length() - 1; i >= 0; i --) {
            map[(int)t.charAt(i) - 'a'] --;
        }
        for (int flag : map) {
            if (flag != 0) {
                return false;
            }
        }
        return true;
    }
}