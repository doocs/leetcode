impl Solution {
    pub fn query_conversions(conversions: Vec<Vec<i32>>, queries: Vec<Vec<i32>>) -> Vec<i32> {
        const MOD: i64 = 1_000_000_007;
        let n = conversions.len() + 1;

        let mut g = vec![Vec::<(usize, i64)>::new(); n];
        for e in conversions {
            g[e[0] as usize].push((e[1] as usize, e[2] as i64));
        }

        let mut res = vec![0_i64; n];
        let mut stk = vec![(0_usize, 1_i64)];
        while let Some((s, mul)) = stk.pop() {
            res[s] = mul;
            for &(t, w) in &g[s] {
                stk.push((t, mul * w % MOD));
            }
        }

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
