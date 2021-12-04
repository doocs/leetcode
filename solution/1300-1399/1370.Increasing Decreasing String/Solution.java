class Solution {
    public String sortString(String s) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < s.length()) {
            for (int i = 0; i < 26; ++i) {
                if (counter[i] > 0) {
                    sb.append((char) ('a' + i));
                    --counter[i];
                }
            }
            for (int i = 25; i >= 0; --i) {
                if (counter[i] > 0) {
                    sb.append((char) ('a' + i));
                    --counter[i];
                }
            }
        }
        return sb.toString();
    }
}