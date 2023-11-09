impl Solution {
    pub fn min_count(coins: Vec<i32>) -> i32 {
        coins
            .iter()
            .map(|&x| (x + 1) >> 1)
            .sum::<i32>()
    }
}
