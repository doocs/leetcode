class Solution {
    public int minimizeXor(int num1, int num2) {
        int cnt1 = Integer.bitCount(num1);
        int cnt2 = Integer.bitCount(num2);
        for (; cnt1 > cnt2; --cnt1) {
            num1 &= (num1 - 1);
        }
        for (; cnt1 < cnt2; ++cnt1) {
            num1 |= (num1 + 1);
        }
        return num1;
    }
}