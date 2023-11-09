use std::collections::VecDeque;

impl Solution {
    #[allow(dead_code)]
    pub fn max_sliding_window(nums: Vec<i32>, k: i32) -> Vec<i32> {
        // The deque contains the index of `nums`
        let mut q: VecDeque<usize> = VecDeque::new();
        let mut ans_vec: Vec<i32> = Vec::new();

        for i in 0..nums.len() {
            // Check the first element of queue, if it's out of bound
            if !q.is_empty() && (i as i32) - k + 1 > (*q.front().unwrap() as i32) {
                // Pop it out
                q.pop_front();
            }
            // Pop back elements out until either the deque is empty
            // Or the back element is greater than the current traversed element
            while !q.is_empty() && nums[*q.back().unwrap()] <= nums[i] {
                q.pop_back();
            }
            // Push the current index in queue
            q.push_back(i);
            // Check if the condition is satisfied
            if i >= ((k - 1) as usize) {
                ans_vec.push(nums[*q.front().unwrap()]);
            }
        }

        ans_vec
    }
}
