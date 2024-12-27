use std::collections::VecDeque;

impl Solution {
    pub fn max_sliding_window(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let k = k as usize;
        let mut ans = Vec::new();
        let mut q: VecDeque<usize> = VecDeque::new();

        for i in 0..nums.len() {
            if let Some(&front) = q.front() {
                if i >= front + k {
                    q.pop_front();
                }
            }
            while let Some(&back) = q.back() {
                if nums[back] <= nums[i] {
                    q.pop_back();
                } else {
                    break;
                }
            }
            q.push_back(i);
            if i >= k - 1 {
                ans.push(nums[*q.front().unwrap()]);
            }
        }
        ans
    }
}
