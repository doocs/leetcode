public class Solution {
    public int[] ConstructArr(int[] a) {
        int n = a.Length;
        int[] ans = new int[n];
        int left = 1, right = 1;
        for (int i = 0; i < n; i++) {
            ans[i] = left;
            left *= a[i];
        }
        for (int i = n - 1; i > -1; i--) {
            ans[i] *= right;
            right *= a[i];
        }
        return ans;
    }
}