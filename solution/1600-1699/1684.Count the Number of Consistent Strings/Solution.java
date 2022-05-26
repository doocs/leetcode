class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        boolean[] chars = new boolean[26];
        for (char c : allowed.toCharArray()) {
            chars[c - 'a'] = true;
        }
        int res = 0;
        for (String word : words) {
            boolean find = true;
            for (char c : word.toCharArray()) {
                if (!chars[c - 'a']) {
                    find = false;
                    break;
                }
            }
            if (find) {
                ++res;
            }
        }
        return res;
    }
}