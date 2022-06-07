use std::collections::VecDeque;
impl Solution {
    pub fn max_sliding_window(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let k = k as usize;
        let n = nums.len();
        if n == 0 || k == 0 {
            return Vec::new();
        }
        let mut res = vec![0; n - k + 1];
        let mut queue = VecDeque::new();
        for i in 0..k {
            while !queue.is_empty() && *queue.back().unwrap() < nums[i] {
                queue.pop_back();
            }
            queue.push_back(nums[i]);
        }
        res[0] = queue[0];
        for i in k..n {
            if nums[i - k] == queue[0] {
                queue.pop_front();
            }
            while !queue.is_empty() && *queue.back().unwrap() < nums[i] {
                queue.pop_back();
            }
            queue.push_back(nums[i]);
            res[i - k + 1] = queue[0];
        }
        res
    }
}
