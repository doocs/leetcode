impl Solution {
    pub fn fill_cups(mut amount: Vec<i32>) -> i32 {
        amount.sort();
        let dif = amount[0] + amount[1] - amount[2];
        if dif <= 0 {
            return amount[2];
        }
        (dif + 1) / 2 + amount[2]
    }
}
