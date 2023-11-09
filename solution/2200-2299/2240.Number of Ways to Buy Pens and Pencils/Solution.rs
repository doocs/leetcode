impl Solution {
    pub fn ways_to_buy_pens_pencils(total: i32, cost1: i32, cost2: i32) -> i64 {
        let mut ans: i64 = 0;
        for pen in 0..=total / cost1 {
            ans += (((total - pen * cost1) / cost2) as i64) + 1;
        }
        ans
    }
}
