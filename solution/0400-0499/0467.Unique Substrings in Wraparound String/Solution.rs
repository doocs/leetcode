impl Solution {
    pub fn find_substring_in_wrapround_string(p: String) -> i32 {
        let n = p.len();
        let p = p.as_bytes();
        let mut dp = [0; 26];
        let mut cur = 1;
        dp[(p[0] - b'a') as usize] = 1;
        for i in 1..n {
            if (p[i] - p[i - 1] + 25) % 26 == 0 {
                cur += 1;
            } else {
                cur = 1;
            }
            let index = (p[i] - b'a') as usize;
            dp[index] = dp[index].max(cur);
        }
        dp.into_iter().sum()
    }
}
