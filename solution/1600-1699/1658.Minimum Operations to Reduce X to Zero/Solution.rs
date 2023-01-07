impl Solution {
    pub fn min_operations(nums: Vec<i32>, x: i32) -> i32 {
        let n = nums.len();
        let target = nums.iter().sum::<i32>() - x;
        if target < 0 {
            return -1;
        }
        let mut ans = i32::MAX;
        let mut sum = 0;
        let mut i = 0;
        for j in 0..n {
            sum += nums[j];
            while sum > target {
                sum -= nums[i];
                i += 1;
            }
            if sum == target {
                ans = ans.min((n - 1 - (j - i)) as i32)
            }
        }
        if ans == i32::MAX {
            return -1;
        }
        ans
    }
}
