class Solution {
    public boolean checkIfPangram(String sentence) {
        int res = 0;
        for (int i = 0; i < sentence.length(); ++i) {
            int diff = sentence.charAt(i) - 'a';
            res |= (1 << diff);
            if (res == 0x3ffffff) return true;
        }
        return false;
    }
}