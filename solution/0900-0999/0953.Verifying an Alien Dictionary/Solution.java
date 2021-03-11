class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] index = new int[26];
        for (int i = 0; i < 26; ++i) {
            index[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0, m = words.length; i < m - 1; ++i) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int len1 = word1.length();
            int len2 = word2.length();
            boolean flag = true;
            for (int j = 0, n = Math.min(len1, len2); j < n && flag; ++j) {
                int diff = index[word1.charAt(j) - 'a'] - index[word2.charAt(j) - 'a'];
                if (diff > 0) return false;
                if (diff < 0) flag = false;
            }
            if (flag && len1 > len2) return false;
        }
        return true;
    }
}