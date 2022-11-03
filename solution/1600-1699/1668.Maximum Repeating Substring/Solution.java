class Solution {
    public int maxRepeating(String sequence, String word) {
        for (int k = sequence.length() / word.length(); k > 0; --k) {
            if (sequence.contains(word.repeat(k))) {
                return k;
            }
        }
        return 0;
    }
}