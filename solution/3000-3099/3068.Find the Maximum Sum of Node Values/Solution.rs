impl Solution {
    pub fn maximum_value_sum(nums: Vec<i32>, k: i32, edges: Vec<Vec<i32>>) -> i64 {
        let mut f0: i64 = 0;
        let mut f1: i64 = i64::MIN;

        for &x in &nums {
            let tmp = f0;
            f0 = std::cmp::max(f0 + x as i64, f1 + (x ^ k) as i64);
            f1 = std::cmp::max(f1 + x as i64, tmp + (x ^ k) as i64);
        }

        f0
    }
}
