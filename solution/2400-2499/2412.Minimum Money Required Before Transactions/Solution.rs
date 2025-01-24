impl Solution {
    pub fn minimum_money(transactions: Vec<Vec<i32>>) -> i64 {
        let mut s: i64 = 0;
        for transaction in &transactions {
            let (a, b) = (transaction[0], transaction[1]);
            s += (a - b).max(0) as i64;
        }
        let mut ans = 0;
        for transaction in &transactions {
            let (a, b) = (transaction[0], transaction[1]);
            if a > b {
                ans = ans.max(s + b as i64);
            } else {
                ans = ans.max(s + a as i64);
            }
        }
        ans
    }
}
