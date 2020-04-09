using System;

public class Solution {
    public void Rotate(int[] nums, int k) {
        if (nums.Length == 0 || k % nums.Length == 0) return;
        k = k % nums.Length;
        Algo2(nums, k);
    }
    
    private void Algo1(int[] nums, int k)
    {
        var copy = new int[nums.Length];
        Array.Copy(nums, copy, nums.Length);
        var j = nums.Length - k;
        for (var i = 0; i < nums.Length; ++i)
        {
            if (j == nums.Length) j = 0;
            nums[i] = copy[j];
            ++j;
        }
    }
    
    private void Algo2(int[] nums, int k)
    {
        var gcd = Gcd(nums.Length, k);
        var count = nums.Length / gcd;
        for (var i = 0; i < gcd; ++i)
        {
            var p = i;
            var first = nums[p]; 
            for (var j = 0; j + 1 < count; ++j)
            {
                var q = p - k;
                if (q < 0) q += nums.Length;
                nums[p] = nums[q];
                p = q;
            }
            nums[i + k] = first;
        }
    }
    
    private int Gcd(int m, int n)
    {
        while (n > 0)
        {
            var x = m % n;
            m = n;
            n = x;
        }
        return m;
    }
}