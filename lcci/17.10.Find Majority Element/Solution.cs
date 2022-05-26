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
        cnt = 0;
        foreach (int num in nums)
        {
            if (candidate == num)
            {
                ++cnt;
            }
        }
        return cnt > nums.Length / 2 ? candidate : -1;
    }
}