impl Solution {
    pub fn number_of_ways(corridor: String) -> i32 {
        let n = corridor.len();
        let bytes = corridor.as_bytes();
        let modv: i32 = 1_000_000_007;
        let mut f = vec![[0; 3]; n + 1];
        f[n][2] = 1;
        for i in (0..n).rev() {
            for k in 0..3 {
                let mut nk = k;
                if bytes[i] == b'S' {
                    nk += 1;
                }
                if nk > 2 {
                    continue;
                }
                f[i][k] = f[i + 1][nk];
                if nk == 2 {
                    f[i][k] = (f[i][k] + f[i + 1][0]) % modv;
                }
            }
        }
        f[0][0]
    }
}
