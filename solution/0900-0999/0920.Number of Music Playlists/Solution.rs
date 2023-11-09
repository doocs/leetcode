impl Solution {
    #[allow(dead_code)]
    pub fn num_music_playlists(n: i32, goal: i32, k: i32) -> i32 {
        let mut dp: Vec<Vec<i64>> = vec![vec![0; n as usize + 1]; goal as usize + 1];

        // Initialize the dp vector
        dp[0][0] = 1;

        // Begin the dp process
        for i in 1..=goal as usize {
            for j in 1..=n as usize {
                // Choose the song that has not been chosen before
                // We have n - (j - 1) songs to choose
                dp[i][j] += dp[i - 1][j - 1] * ((n - ((j as i32) - 1)) as i64);

                // Choose the song that has been chosen before
                // We have j - k songs to choose if j > k
                if (j as i32) > k {
                    dp[i][j] += dp[i - 1][j] * (((j as i32) - k) as i64);
                }

                // Update dp[i][j]
                dp[i][j] %= ((1e9 as i32) + 7) as i64;
            }
        }

        dp[goal as usize][n as usize] as i32
    }
}
