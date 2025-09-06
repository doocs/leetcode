impl Solution {
    pub fn longest_subarray(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut left = vec![0; n + 1];
        let mut right = vec![0; n + 1];

        for i in 1..=n {
            if nums[i - 1] == 1 {
                left[i] = left[i - 1] + 1;
            }
        }

        for i in (0..n).rev() {
            if nums[i] == 1 {
                right[i] = right[i + 1] + 1;
            }
        }

        let mut ans = 0;
        for i in 0..n {
            ans = ans.max(left[i] + right[i + 1]);
        }

        ans as i32
    }
}
