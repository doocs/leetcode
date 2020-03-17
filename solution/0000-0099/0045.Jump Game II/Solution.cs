using System;

public class Solution {
    public int Jump(int[] nums) {
        var steps = 0;
        var maxJump = 0;
        var i = 0;
        while (maxJump + 1 < nums.Length)
        {
            var newMaxJump = maxJump;
            for (var j = i; j < nums.Length && j <= maxJump; ++j)
            {
                newMaxJump = Math.Max(newMaxJump, j + nums[j]);
            }
            i = maxJump + 1;
            if (newMaxJump > maxJump)
            {
                maxJump = newMaxJump;
                ++steps;
            }
            else
            {
                break;
            }
        }
        if (maxJump + 1 >= nums.Length) return steps;
        return -1;
    }
}