use std::collections::HashMap;

impl Solution {
    pub fn longest_subarray(nums: Vec<i32>, k: i32) -> i32 {
        let mut cnt = HashMap::new();
        let mut ans = 0;
        let mut cur = 0;
        let mut l = 0;

        for r in 0..nums.len() {
            let entry = cnt.entry(nums[r]).or_insert(0);
            *entry += 1;
            if *entry == 2 {
                cur += 1;
            }

            while cur > k {
                let entry = cnt.entry(nums[l]).or_insert(0);
                *entry -= 1;
                if *entry == 1 {
                    cur -= 1;
                }
                l += 1;
            }

            ans = ans.max(r - l + 1);
        }

        ans as i32
    }
}
