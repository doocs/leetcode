use std::collections::HashMap;
impl Solution {
    pub fn unequal_triplets(nums: Vec<i32>) -> i32 {
        let mut cnt = HashMap::new();
        for num in nums.iter() {
            *cnt.entry(num).or_insert(0) += 1;
        }
        let n = nums.len();
        let mut ans = 0;
        let mut a = 0;
        for v in cnt.values() {
            let b = n - a - v;
            ans += v * a * b;
            a += v;
        }
        ans as i32
    }
}
