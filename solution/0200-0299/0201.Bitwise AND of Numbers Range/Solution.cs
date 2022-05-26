public class Solution {
    public int RangeBitwiseAnd(int left, int right) {
        while (left < right)
        {
            right &= (right - 1);
        }
        return right;
    }
}