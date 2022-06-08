impl Solution {
    pub fn nth_ugly_number(n: i32) -> i32 {
        let n = n as usize;
        let mut dp = vec![1; n];
        let mut p2 = 0;
        let mut p3 = 0;
        let mut p5 = 0;
        for i in 1..n {
            let n2 = dp[p2] * 2;
            let n3 = dp[p3] * 3;
            let n5 = dp[p5] * 5;
            dp[i] = n2.min(n3.min(n5));

            if dp[i] == n2 {
                p2 += 1;
            };
            if dp[i] == n3 {
                p3 += 1;
            };
            if dp[i] == n5 {
                p5 += 1;
            };
        }
        dp[n - 1]
    }
}
