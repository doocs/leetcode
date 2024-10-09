impl Solution {
    pub fn max_coins(mut piles: Vec<i32>) -> i32 {
        piles.sort();
        let mut ans = 0;
        for i in (piles.len() / 3..piles.len()).step_by(2) {
            ans += piles[i];
        }
        ans
    }
}
