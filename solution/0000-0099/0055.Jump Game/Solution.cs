using System;

public class Solution {
    public bool CanJump(int[] nums) {
        var maxJump = 0;
        for (var i = 0; i < nums.Length; ++i)
        {
            if (i <= maxJump)
            {
                maxJump = Math.Max(maxJump, i + nums[i]);
            }
            else
            {
                return false;
            }
        }
        return true;
    }
}