impl Solution {
    pub fn special_perm(nums: Vec<i32>) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let n = nums.len();
        let m = 1 << n;
        let mut f = vec![vec![0; n]; m];

        for i in 1..m {
            for j in 0..n {
                if (i >> j) & 1 == 1 {
                    let ii = i ^ (1 << j);
                    if ii == 0 {
                        f[i][j] = 1;
                        continue;
                    }
                    for k in 0..n {
                        if nums[j] % nums[k] == 0 || nums[k] % nums[j] == 0 {
                            f[i][j] = (f[i][j] + f[ii][k]) % MOD;
                        }
                    }
                }
            }
        }

        let mut ans = 0;
        for &x in &f[m - 1] {
            ans = (ans + x) % MOD;
        }

        ans
    }
}
