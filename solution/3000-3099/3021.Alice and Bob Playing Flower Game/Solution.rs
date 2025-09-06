impl Solution {
    pub fn flower_game(n: i32, m: i32) -> i64 {
        let a1 = ((n + 1) / 2) as i64;
        let b1 = ((m + 1) / 2) as i64;
        let a2 = (n / 2) as i64;
        let b2 = (m / 2) as i64;
        a1 * b2 + a2 * b1
    }
}
