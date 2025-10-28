use std::collections::HashMap;

impl Solution {
    pub fn get_largest_outlier(nums: Vec<i32>) -> i32 {
        let mut s = 0;
        let mut cnt = HashMap::new();
        for &x in &nums {
            s += x;
            *cnt.entry(x).or_insert(0) += 1;
        }

        let mut ans = i32::MIN;
        for (&x, &v) in &cnt {
            let t = s - x;
            if t % 2 != 0 {
                continue;
            }
            let y = t / 2;
            if let Some(&count_y) = cnt.get(&y) {
                if x != y || v > 1 {
                    ans = ans.max(x);
                }
            }
        }
        ans
    }
}
