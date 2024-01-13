class Solution {
    public int differenceOfSums(int n, int m) {
        int sum = n * (n + 1) / 2;
        int k = n / m;
        int nums2 = k * (k + 1) / 2 * m;
        return sum - nums2 * 2;
    }
}