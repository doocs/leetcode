impl Solution {
    pub fn max_profit(prices: Vec<i32>, strategy: Vec<i32>, k: i32) -> i64 {
        let n: usize = prices.len();
        let k: usize = k as usize;

        let mut s: Vec<i64> = vec![0; n + 1];
        let mut t: Vec<i64> = vec![0; n + 1];

        for i in 1..=n {
            let a: i64 = prices[i - 1] as i64;
            let b: i64 = strategy[i - 1] as i64;
            s[i] = s[i - 1] + a * b;
            t[i] = t[i - 1] + a;
        }

        let mut ans: i64 = s[n];
        for i in k..=n {
            let cur = s[n] - (s[i] - s[i - k]) + (t[i] - t[i - k / 2]);
            if cur > ans {
                ans = cur;
            }
        }

        ans
    }
}
