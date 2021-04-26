class Solution {
    public char firstUniqChar(String s) {
        int n;
        if ((n = s.length()) == 0) return ' ';
        int[] counter = new int[26];
        for (int i = 0; i < n; ++i) {
            int index = s.charAt(i) - 'a';
            ++counter[index];
        }
        for (int i = 0; i < n; ++i) {
            int index = s.charAt(i) - 'a';
            if (counter[index] == 1) return s.charAt(i);
        }
        return ' ';
    }
}