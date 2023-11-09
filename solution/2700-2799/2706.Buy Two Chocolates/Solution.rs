impl Solution {
    pub fn buy_choco(mut prices: Vec<i32>, money: i32) -> i32 {
        prices.sort();

        let sum = prices[0] + prices[1];
        if sum > money {
            return money;
        }

        money - sum
    }
}
