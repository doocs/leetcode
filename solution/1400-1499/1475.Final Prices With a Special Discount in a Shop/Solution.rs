impl Solution {
    pub fn final_prices(mut prices: Vec<i32>) -> Vec<i32> {
        let mut stk: Vec<i32> = Vec::new();
        for i in (0..prices.len()).rev() {
            let x = prices[i];
            while !stk.is_empty() && x < *stk.last().unwrap() {
                stk.pop();
            }
            if let Some(&top) = stk.last() {
                prices[i] -= top;
            }
            stk.push(x);
        }
        prices
    }
}
