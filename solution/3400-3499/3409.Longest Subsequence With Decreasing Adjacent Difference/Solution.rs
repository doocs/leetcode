impl Solution {
    pub fn longest_subsequence(nums: Vec<i32>) -> i32 {
        let mx = *nums.iter().max().unwrap();
        let mn = *nums.iter().min().unwrap();
        let diff = (mx - mn) as usize;

        let mut dp = vec![vec![0; diff + 1]; (mx + 1) as usize];

        let mut ans = 0;

        for &n in nums.iter() {
            let n_usize = n as usize;
            let mut maxnum = 1;

            for d in (0..=diff).rev() {
                if n_usize + d <= mx as usize {
                    if dp[n_usize + d][d] + 1 > maxnum {
                        maxnum = dp[n_usize + d][d] + 1;
                    }
                }
                if n_usize >= d {
                    if dp[n_usize - d][d] + 1 > maxnum {
                        maxnum = dp[n_usize - d][d] + 1;
                    }
                }
                dp[n_usize][d] = maxnum;
                ans = ans.max(maxnum);
            }
        }

        ans
    }
}
