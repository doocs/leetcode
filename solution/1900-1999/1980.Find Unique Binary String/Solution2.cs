public class Solution {
    public string FindDifferentBinaryString(string[] nums) {
        int n = nums.Length;
        char[] ans = new char[n];
        for (int i = 0; i < n; i++) {
            ans[i] = nums[i][i] == '0' ? '1' : '0';
        }
        return new string(ans);
    }
}
