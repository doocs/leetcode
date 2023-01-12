impl Solution {
    pub fn max_coins(mut piles: Vec<i32>) -> i32 {
        piles.sort();
        let n = piles.len();
        let mut ans = 0;
        for i in 1..=n / 3 {
            ans += piles[n - 2 * i];
        }
        ans
    }
}
