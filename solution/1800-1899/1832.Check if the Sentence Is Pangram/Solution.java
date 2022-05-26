class Solution {
    public boolean checkIfPangram(String sentence) {
        int res = 0;
        for (char c : sentence.toCharArray()) {
            res |= (1 << (c - 'a'));
            if (res == 0x3ffffff) {
                return true;
            }
        }
        return false;
    }
}