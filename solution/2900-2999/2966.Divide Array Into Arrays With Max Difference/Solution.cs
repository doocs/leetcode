public class Solution {
    public int[][] DivideArray(int[] nums, int k) {
        Array.Sort(nums);
        List<int[]> ans = new List<int[]>();

        for (int i = 0; i < nums.Length; i += 3) {
            if (i + 2 >= nums.Length) {
                return new int[0][];
            }

            int[] t = new int[] { nums[i], nums[i + 1], nums[i + 2] };
            if (t[2] - t[0] > k) {
                return new int[0][];
            }

            ans.Add(t);
        }

        return ans.ToArray();
    }
}
