impl Solution {
    pub fn sum_of_beauties(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut right: Vec<i32> = vec![0; n];
        right[n - 1] = nums[n - 1];
        for i in (1..n - 1).rev() {
            right[i] = right[i + 1].min(nums[i]);
        }
        let mut ans = 0;
        let mut l = nums[0];
        for i in 1..n - 1 {
            let r = right[i + 1];
            if l < nums[i] && nums[i] < r {
                ans += 2;
            } else if nums[i - 1] < nums[i] && nums[i] < nums[i + 1] {
                ans += 1;
            }
            l = l.max(nums[i]);
        }
        ans
    }
}
