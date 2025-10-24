class Solution {
    public int totalMoney(int n) {
        int k = n / 7, b = n % 7;
        int s1 = (28 + 28 + 7 * (k - 1)) * k / 2;
        int s2 = (k + 1 + k + 1 + b - 1) * b / 2;
        return s1 + s2;
    }
}
