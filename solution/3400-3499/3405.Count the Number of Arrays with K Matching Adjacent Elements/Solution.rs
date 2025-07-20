impl Solution {
    pub fn count_good_arrays(n: i32, m: i32, k: i32) -> i32 {
        const N: usize = 1e5 as usize + 10;
        const MOD: i64 = 1_000_000_007;
        use std::sync::OnceLock;

        static F: OnceLock<Vec<i64>> = OnceLock::new();
        static G: OnceLock<Vec<i64>> = OnceLock::new();

        fn qpow(mut a: i64, mut k: i64, m: i64) -> i64 {
            let mut res = 1;
            while k != 0 {
                if k & 1 == 1 {
                    res = res * a % m;
                }
                a = a * a % m;
                k >>= 1;
            }
            res
        }

        fn init() -> (&'static Vec<i64>, &'static Vec<i64>) {
            F.get_or_init(|| {
                let mut f = vec![1i64; N];
                for i in 1..N {
                    f[i] = f[i - 1] * i as i64 % MOD;
                }
                f
            });

            G.get_or_init(|| {
                let f = F.get().unwrap();
                let mut g = vec![1i64; N];
                for i in 1..N {
                    g[i] = qpow(f[i], MOD - 2, MOD);
                }
                g
            });

            (F.get().unwrap(), G.get().unwrap())
        }

        fn comb(f: &[i64], g: &[i64], m: usize, n: usize) -> i64 {
            f[m] * g[n] % MOD * g[m - n] % MOD
        }

        let (f, g) = init();
        let n = n as usize;
        let m = m as i64;
        let k = k as usize;

        let c = comb(f, g, n - 1, k);
        let pow = qpow(m - 1, (n - 1 - k) as i64, MOD);
        (c * m % MOD * pow % MOD) as i32
    }
}
