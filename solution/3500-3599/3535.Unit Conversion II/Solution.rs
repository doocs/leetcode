impl Solution {
    pub fn query_conversions(conversions: Vec<Vec<i32>>, queries: Vec<Vec<i32>>) -> Vec<i32> {
        const MOD: i64 = 1_000_000_007;
        let n = conversions.len() + 1;

        let mut g = vec![Vec::<(usize, i64)>::new(); n];
        for e in conversions {
            g[e[0] as usize].push((e[1] as usize, e[2] as i64));
        }

        let mut res = vec![0_i64; n];

        fn dfs(s: usize, mul: i64, g: &Vec<Vec<(usize, i64)>>, res: &mut Vec<i64>) {
            res[s] = mul;
            for &(t, w) in &g[s] {
                dfs(t, mul * w % MOD, g, res);
            }
        }

        dfs(0, 1, &g, &mut res);

        fn qpow(mut x: i64, mut n: i32) -> i64 {
            let mut res = 1_i64;
            while n > 0 {
                if n & 1 == 1 {
                    res = res * x % MOD;
                }
                x = x * x % MOD;
                n >>= 1;
            }
            res
        }

        let mut ans = Vec::with_capacity(queries.len());
        for q in queries {
            let x = q[0] as usize;
            let y = q[1] as usize;
            ans.push((res[y] * qpow(res[x], 1_000_000_005) % MOD) as i32);
        }
        ans
    }
}
