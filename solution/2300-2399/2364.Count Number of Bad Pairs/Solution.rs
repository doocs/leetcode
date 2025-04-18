use std::collections::HashMap;

impl Solution {
    pub fn count_bad_pairs(nums: Vec<i32>) -> i64 {
        let mut cnt: HashMap<i32, i64> = HashMap::new();
        let mut ans: i64 = 0;
        for (i, &num) in nums.iter().enumerate() {
            let x = i as i32 - num;
            let count = *cnt.get(&x).unwrap_or(&0);
            ans += i as i64 - count;
            *cnt.entry(x).or_insert(0) += 1;
        }
        ans
    }
}
