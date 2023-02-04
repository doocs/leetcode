use std::collections::VecDeque;
impl Solution {
    pub fn max_sliding_window(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let k = k as usize;
        let n = nums.len();
        let mut ans = vec![0; n - k + 1];
        let mut q = VecDeque::new();
        for i in 0..n {
            while !q.is_empty() && i - q[0] + 1 > k {
                q.pop_front();
            }
            while !q.is_empty() && nums[*q.back().unwrap()] <= nums[i] {
                q.pop_back();
            }
            q.push_back(i);
            if i >= k - 1 {
                ans[i - k + 1] = nums[q[0]]
            }
        }
        ans
    }
}