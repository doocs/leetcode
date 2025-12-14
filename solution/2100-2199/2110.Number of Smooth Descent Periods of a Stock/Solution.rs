impl Solution {
    pub fn get_descent_periods(prices: Vec<i32>) -> i64 {
        let mut ans: i64 = 0;
        let n: usize = prices.len();
        let mut i: usize = 0;

        while i < n {
            let mut j: usize = i + 1;
            while j < n && prices[j - 1] - prices[j] == 1 {
                j += 1;
            }
            let cnt: i64 = (j - i) as i64;
            ans += (1 + cnt) * cnt / 2;
            i = j;
        }

        ans
    }
}
