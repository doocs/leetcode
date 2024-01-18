class Solution {
    public int minimumOneBitOperations(int n) {
        if (n == 0) {
            return 0;
        }
        return n ^ minimumOneBitOperations(n >> 1);
    }
}