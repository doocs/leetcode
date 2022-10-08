class Solution {
    public int maxRepeating(String sequence, String word) {
        int x = sequence.length() / word.length();
        for (int k = x; k > 0; --k) {
            if (sequence.contains(word.repeat(k))) {
                return k;
            }
        }
        return 0;
    }
}