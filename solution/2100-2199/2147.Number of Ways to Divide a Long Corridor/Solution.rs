impl Solution {
    pub fn number_of_ways(corridor: String) -> i32 {
        let n: usize = corridor.len();
        let bytes = corridor.as_bytes();
        let modv: i32 = 1_000_000_007;

        let mut f = vec![vec![-1; 3]; n];

        fn dfs(
            i: usize,
            k: usize,
            n: usize,
            bytes: &[u8],
            f: &mut Vec<Vec<i32>>,
            modv: i32,
        ) -> i32 {
            if i >= n {
                return if k == 2 { 1 } else { 0 };
            }
            if f[i][k] != -1 {
                return f[i][k];
            }

            let mut nk = k;
            if bytes[i] == b'S' {
                nk += 1;
            }
            if nk > 2 {
                return 0;
            }

            let mut res = dfs(i + 1, nk, n, bytes, f, modv);
            if nk == 2 {
                res = (res + dfs(i + 1, 0, n, bytes, f, modv)) % modv;
            }

            f[i][k] = res;
            res
        }

        dfs(0, 0, n, bytes, &mut f, modv)
    }
}
