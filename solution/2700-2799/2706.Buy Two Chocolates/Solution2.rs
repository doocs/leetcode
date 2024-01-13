impl Solution {
    pub fn buy_choco(prices: Vec<i32>, money: i32) -> i32 {
        let mut a = 1000;
        let mut b = 1000;
        for &x in prices.iter() {
            if x < a {
                b = a;
                a = x;
            } else if x < b {
                b = x;
            }
        }
        let cost = a + b;
        if money < cost {
            money
        } else {
            money - cost
        }
    }
}
