class Solution {
    public bool Check(int[] arr) {
        Array.Sort(arr);
        int diff = arr[1] - arr[0];
        for (int i = 2; i < arr.Length; i++) {
            if (arr[i] - arr[i - 1] != diff) {
                return false;
            }
        }
        return true;
    }

    public IList<bool> CheckArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<bool> ans = new List<bool>();
        for (int i = 0; i < l.Length; i++) {
            int[] arr = new int[r[i] - l[i] + 1];
            for (int j = 0; j < arr.Length; j++) {
                arr[j] = nums[l[i] + j];
            }
            ans.Add(Check(arr));
        }
        return ans;
    }
}
