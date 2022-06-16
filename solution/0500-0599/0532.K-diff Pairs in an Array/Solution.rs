impl Solution {
    pub fn find_pairs(mut nums: Vec<i32>, k: i32) -> i32 {
        nums.sort();
        let n = nums.len();
        let mut res = 0;
        let mut left = 0;
        let mut right = 1;
        while right < n {
            let num = i32::abs(nums[left] - nums[right]);
            if num == k {
                res += 1;
            }
            if num <= k {
                right += 1;
                while right < n && nums[right - 1] == nums[right] {
                    right += 1;
                }
            } else {
                left += 1;
                while left < right && nums[left - 1] == nums[left] {
                    left += 1;
                }
                if left == right {
                    right += 1;
                }
            }
        }
        res
    }
}
