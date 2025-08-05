use std::collections::HashMap;

impl Solution {
    pub fn min_cost(basket1: Vec<i32>, basket2: Vec<i32>) -> i64 {
        let n = basket1.len();
        let mut cnt: HashMap<i32, i32> = HashMap::new();

        for i in 0..n {
            *cnt.entry(basket1[i]).or_insert(0) += 1;
            *cnt.entry(basket2[i]).or_insert(0) -= 1;
        }

        let mut mi = i32::MAX;
        let mut nums = Vec::new();

        for (x, v) in cnt {
            if v % 2 != 0 {
                return -1;
            }
            for _ in 0..(v.abs() / 2) {
                nums.push(x);
            }
            mi = mi.min(x);
        }

        nums.sort();

        let m = nums.len();
        let mut ans = 0;

        for i in 0..(m / 2) {
            ans += nums[i].min(mi * 2) as i64;
        }

        ans
    }
}
