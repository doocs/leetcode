use std::collections::HashMap;

impl Solution {
    pub fn count_good(nums: Vec<i32>, k: i32) -> i64 {
        let mut cnt = HashMap::new();
        let (mut ans, mut cur, mut i) = (0i64, 0i64, 0);

        for &x in &nums {
            cur += *cnt.get(&x).unwrap_or(&0);
            *cnt.entry(x).or_insert(0) += 1;

            while cur - (cnt[&nums[i]] - 1) >= k as i64 {
                *cnt.get_mut(&nums[i]).unwrap() -= 1;
                cur -= cnt[&nums[i]];
                i += 1;
            }

            if cur >= k as i64 {
                ans += (i + 1) as i64;
            }
        }

        ans
    }
}
