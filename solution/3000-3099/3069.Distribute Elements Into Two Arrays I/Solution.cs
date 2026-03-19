public class Solution {
    public int[] ResultArray(int[] nums) {
        int n = nums.Length;
        var arr1 = new List<int> { nums[0] };
        var arr2 = new List<int> { nums[1] };

        for (int k = 2; k < n; ++k) {
            if (arr1[arr1.Count - 1] > arr2[arr2.Count - 1]) {
                arr1.Add(nums[k]);
            } else {
                arr2.Add(nums[k]);
            }
        }

        arr1.AddRange(arr2);
        return arr1.ToArray();
    }
}
