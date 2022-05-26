class Solution {
    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] counter = new int[26];
        for (char c : word1.toCharArray()) {
            ++counter[c - 'a'];
        }
        for (char c : word2.toCharArray()) {
            --counter[c - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (Math.abs(counter[i]) > 3) {
                return false;
            }
        }
        return true;
    }
}