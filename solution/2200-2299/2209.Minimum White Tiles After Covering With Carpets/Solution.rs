impl Solution {
    pub fn minimum_white_tiles(floor: String, num_carpets: i32, carpet_len: i32) -> i32 {
        let n = floor.len();
        let a: Vec<u8> = floor.bytes().collect();
        let m = num_carpets as usize;
        let k = carpet_len as usize;

        let mut s = vec![0i32; n + 1];
        for i in 0..n {
            s[i + 1] = s[i] + if a[i] == b'1' { 1 } else { 0 };
        }

        let mut f = vec![vec![-1; m + 1]; n];

        fn dfs(
            i: usize,
            j: usize,
            n: usize,
            k: usize,
            s: &Vec<i32>,
            f: &mut Vec<Vec<i32>>,
            a: &Vec<u8>,
        ) -> i32 {
            if i >= n {
                return 0;
            }
            if j == 0 {
                return s[n] - s[i];
            }
            if f[i][j] != -1 {
                return f[i][j];
            }

            if s[i + 1] == s[i] {
                let v = dfs(i + 1, j, n, k, s, f, a);
                f[i][j] = v;
                return v;
            }

            let t1 = 1 + dfs(i + 1, j, n, k, s, f, a);
            let ni = i + k;
            let t2 = dfs(ni, j - 1, n, k, s, f, a);

            let t = t1.min(t2);
            f[i][j] = t;
            t
        }

        dfs(0, m, n, k, &s, &mut f, &a)
    }
}
