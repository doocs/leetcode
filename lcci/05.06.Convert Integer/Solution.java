class Solution {
    public int convertInteger(int A, int B) {
        return Integer.bitCount(A ^ B);
    }
}