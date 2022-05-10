impl Solution {
    pub fn min_operations(nums: Vec<i32>, x: i32) -> i32 {
        let n = nums.len();
        let target = nums.iter().sum::<i32>() - x;

        let (mut l, mut r) = (0, 0);
        let (mut sum, mut max) = (0, -1);
        while r < n {
            sum += nums[r];
            r += 1;
            while sum > target && l < r {
                sum -= nums[l];
                l += 1;
            }

            if sum == target {
                max = max.max((r - l) as i32);
            }
        }

        if max == -1 {
            return max;
        }
        return n as i32 - max;
    }
}
