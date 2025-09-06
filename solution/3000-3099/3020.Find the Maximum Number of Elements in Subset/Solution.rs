use std::collections::HashMap;

impl Solution {
    pub fn maximum_length(nums: Vec<i32>) -> i32 {
        let mut cnt: HashMap<i64, i32> = HashMap::new();
        for &x in &nums {
            *cnt.entry(x as i64).or_insert(0) += 1;
        }

        let mut ans = 0;
        if let Some(t) = cnt.remove(&1) {
            ans = t - ((t % 2) ^ 1);
        }

        for &key in cnt.keys() {
            let mut x = key;
            let mut t = 0;
            while *cnt.get(&x).unwrap_or(&0) > 1 {
                x = x * x;
                t += 2;
            }
            t += cnt.get(&x).unwrap_or(&-1);
            ans = ans.max(t);
        }

        ans
    }
}
