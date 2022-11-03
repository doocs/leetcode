impl Solution {
    pub fn max_repeating(sequence: String, word: String) -> i32 {
        let n = sequence.len();
        let m = word.len();
        if n < m {
            return 0;
        }
        let mut dp = vec![0; n - m + 1];
        for i in 0..=n - m {
            let s = &sequence[i..i + m];
            if s == word {
                dp[i] = if (i as i32) - (m as i32) < 0 {
                    0
                } else {
                    dp[i - m]
                } + 1;
            }
        }
        *dp.iter().max().unwrap()
    }
}
