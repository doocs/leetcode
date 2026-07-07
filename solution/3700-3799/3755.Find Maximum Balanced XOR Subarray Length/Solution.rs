use std::collections::HashMap;

impl Solution {
    pub fn max_balanced_subarray(nums: Vec<i32>) -> i32 {
        let mut d = HashMap::new();
        let mut ans = 0;
        let mut a = 0;
        let mut b = nums.len() as i32;

        d.insert(b as i64, -1);

        for (i, &num) in nums.iter().enumerate() {
            a ^= num;
            b += if num % 2 == 0 { 1 } else { -1 };

            let key = ((a as i64) << 32) | b as i64;
            if let Some(&idx) = d.get(&key) {
                ans = ans.max(i as i32 - idx);
            } else {
                d.insert(key, i as i32);
            }
        }

        ans
    }
}
