impl Solution {
    pub fn final_prices(prices: Vec<i32>) -> Vec<i32> {
        let n = prices.len();
        let mut stack = Vec::new();
        let mut res = vec![0; n];
        for i in (0..n).rev() {
            let price = prices[i];
            while !stack.is_empty() && *stack.last().unwrap() > price {
                stack.pop();
            }
            res[i] = price - stack.last().unwrap_or(&0);
            stack.push(price);
        }
        res
    }
}
