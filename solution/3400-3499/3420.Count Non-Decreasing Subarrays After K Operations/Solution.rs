use std::collections::VecDeque;

impl Solution {
    pub fn count_non_decreasing_subarrays(nums: Vec<i32>, k: i32) -> i64 {
        let n = nums.len();
        let mut q: VecDeque<usize> = VecDeque::new();
        let mut ans: i64 = 0;
        let mut cost: i64 = 0;
        let mut r = n as i64 - 1;
        let k = k as i64;
        for l in (0..n).rev() {
            let x = nums[l] as i64;
            while let Some(&s) = q.back() {
                if nums[s] as i64 > x {
                    break;
                }
                q.pop_back();
                let end = q.back().copied().map(|idx| idx as i64).unwrap_or(r + 1);
                cost += (x - nums[s] as i64) * (end - s as i64);
            }
            q.push_back(l);
            while cost > k {
                let s = *q.front().unwrap();
                cost -= nums[s] as i64 - nums[r as usize] as i64;
                if s as i64 == r {
                    q.pop_front();
                }
                r -= 1;
            }
            ans += r - l as i64 + 1;
        }
        ans
    }
}
