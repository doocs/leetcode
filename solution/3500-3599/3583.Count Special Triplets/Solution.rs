use std::collections::HashMap;

impl Solution {
    pub fn special_triplets(nums: Vec<i32>) -> i32 {
        let mut left: HashMap<i32, i64> = HashMap::new();
        let mut right: HashMap<i32, i64> = HashMap::new();

        for &x in &nums {
            *right.entry(x).or_insert(0) += 1;
        }

        let modulo: i64 = 1_000_000_007;
        let mut ans: i64 = 0;

        for &x in &nums {
            if let Some(v) = right.get_mut(&x) {
                *v -= 1;
            }

            let t = x * 2;

            let l = *left.get(&t).unwrap_or(&0);
            let r = *right.get(&t).unwrap_or(&0);

            ans = (ans + (l * r) % modulo) % modulo;

            *left.entry(x).or_insert(0) += 1;
        }

        ans as i32
    }
}
