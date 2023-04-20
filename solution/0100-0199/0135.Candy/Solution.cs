public class Solution {
    public int Candy(int[] ratings) {
        int n = ratings.Length;
        int[] left = new int[n];
        int[] right = new int[n];
        Array.Fill(left, 1);
        Array.Fill(right, 1);
        for (int i = 1; i < n; ++i) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.Max(left[i], right[i]);
        }
        return ans;
    }
}