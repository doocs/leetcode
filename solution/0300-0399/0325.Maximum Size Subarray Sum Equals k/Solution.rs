use std::collections::HashMap;

impl Solution {
    pub fn max_sub_array_len(nums: Vec<i32>, k: i32) -> i32 {
        let mut d = HashMap::new();
        d.insert(0, -1);
        let mut ans = 0;
        let mut s = 0;

        for (i, &x) in nums.iter().enumerate() {
            s += x;
            if let Some(&j) = d.get(&(s - k)) {
                ans = ans.max((i as i32) - j);
            }
            d.entry(s).or_insert(i as i32);
        }

        ans
    }
}
