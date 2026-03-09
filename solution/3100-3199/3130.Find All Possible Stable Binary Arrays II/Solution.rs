impl Solution {
    pub fn number_of_stable_arrays(zero: i32, one: i32, limit: i32) -> i32 {
        const MOD: i64 = 1_000_000_007;

        fn dfs(
            i: i32,
            j: i32,
            k: usize,
            limit: i32,
            f: &mut Vec<Vec<[i64; 2]>>,
        ) -> i64 {
            if i < 0 || j < 0 {
                return 0;
            }

            if i == 0 {
                return if k == 1 && j <= limit { 1 } else { 0 };
            }

            if j == 0 {
                return if k == 0 && i <= limit { 1 } else { 0 };
            }

            let (iu, ju) = (i as usize, j as usize);

            if f[iu][ju][k] != -1 {
                return f[iu][ju][k];
            }

            let res = if k == 0 {
                (
                    dfs(i - 1, j, 0, limit, f)
                    + dfs(i - 1, j, 1, limit, f)
                    - dfs(i - limit - 1, j, 1, limit, f)
                    + MOD
                ) % MOD
            } else {
                (
                    dfs(i, j - 1, 0, limit, f)
                    + dfs(i, j - 1, 1, limit, f)
                    - dfs(i, j - limit - 1, 0, limit, f)
                    + MOD
                ) % MOD
            };

            f[iu][ju][k] = res;
            res
        }

        let mut f = vec![vec![[-1_i64; 2]; (one + 1) as usize]; (zero + 1) as usize];

        ((dfs(zero, one, 0, limit, &mut f) + dfs(zero, one, 1, limit, &mut f)) % MOD) as i32
    }
}
