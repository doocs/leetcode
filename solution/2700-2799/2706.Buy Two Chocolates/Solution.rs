impl Solution {
    pub fn buy_choco(mut prices: Vec<i32>, money: i32) -> i32 {
        prices.sort();
        let cost = prices[0] + prices[1];
        if cost > money {
            return money;
        }
        money - cost
    }
}
