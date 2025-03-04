use std::collections::HashMap;

impl Solution {
    pub fn min_subarray(nums: Vec<i32>, p: i32) -> i32 {
        let mut k = 0;
        for &x in &nums {
            k = (k + x) % p;
        }
        if k == 0 {
            return 0;
        }

        let mut last = HashMap::new();
        last.insert(0, -1);
        let n = nums.len();
        let mut ans = n as i32;
        let mut cur = 0;

        for i in 0..n {
            cur = (cur + nums[i]) % p;
            let target = (cur - k + p) % p;
            if let Some(&prev_idx) = last.get(&target) {
                ans = ans.min(i as i32 - prev_idx);
            }
            last.insert(cur, i as i32);
        }

        if ans == n as i32 {
            -1
        } else {
            ans
        }
    }
}
