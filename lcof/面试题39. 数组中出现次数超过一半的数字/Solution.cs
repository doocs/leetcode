public class Solution {
    public int MajorityElement(int[] nums) {
        int cnt = 0, candidate = 0;
        foreach (int num in nums)
        {
            if (cnt == 0)
            {
                candidate = num;
            }
            cnt += (candidate == num ? 1 : -1);
        }
        return candidate;
    }
}