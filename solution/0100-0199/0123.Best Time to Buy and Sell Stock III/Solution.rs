impl Solution {
    #[allow(dead_code)]
    pub fn max_profit(prices: Vec<i32>) -> i32 {
        let mut f1 = -prices[0];
        let mut f2 = 0;
        let mut f3 = -prices[0];
        let mut f4 = 0;
        let n = prices.len();

        for i in 1..n {
            f1 = std::cmp::max(f1, -prices[i]);
            f2 = std::cmp::max(f2, f1 + prices[i]);
            f3 = std::cmp::max(f3, f2 - prices[i]);
            f4 = std::cmp::max(f4, f3 + prices[i]);
        }

        f4
    }
}
