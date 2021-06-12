public class Solution {
    public int MajorityElement(int[] nums) {
        int cnt = 0, major = 0;
        foreach (int num in nums)
        {
            if (cnt == 0)
            {
                major = num;
                cnt = 1;
            }
            else
            {
                cnt += (major == num ? 1 : -1);
            }
        }
        return major;
    }
}