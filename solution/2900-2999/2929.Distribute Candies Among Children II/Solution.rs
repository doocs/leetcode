impl Solution {
    pub fn distribute_candies(n: i32, limit: i32) -> i64 {
        if n > 3 * limit {
            return 0;
        }
        let mut ans = Self::comb2(n + 2);
        if n > limit {
            ans -= 3 * Self::comb2(n - limit + 1);
        }
        if n - 2 >= 2 * limit {
            ans += 3 * Self::comb2(n - 2 * limit);
        }
        ans
    }

    fn comb2(n: i32) -> i64 {
        (n as i64) * (n as i64 - 1) / 2
    }
}
