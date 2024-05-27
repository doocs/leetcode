impl Solution {
    pub fn missing_rolls(rolls: Vec<i32>, mean: i32, n: i32) -> Vec<i32> {
        let m = rolls.len() as i32;
        let s = (n + m) * mean - rolls.iter().sum::<i32>();

        if s > n * 6 || s < n {
            return vec![];
        }

        let mut ans = vec![s / n; n as usize];
        for i in 0..(s % n) as usize {
            ans[i] += 1;
        }

        ans
    }
}
