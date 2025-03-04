impl Solution {
    pub fn palindrome_partition(s: String, k: i32) -> i32 {
        let n = s.len();
        let s: Vec<char> = s.chars().collect();
        let mut g = vec![vec![0; n]; n];

        for i in (0..n).rev() {
            for j in i + 1..n {
                g[i][j] = if s[i] != s[j] { 1 } else { 0 };
                if i + 1 < j {
                    g[i][j] += g[i + 1][j - 1];
                }
            }
        }

        let mut f = vec![vec![0; (k + 1) as usize]; n + 1];
        let inf = i32::MAX;

        for i in 1..=n {
            for j in 1..=i.min(k as usize) {
                if j == 1 {
                    f[i][j] = g[0][i - 1];
                } else {
                    f[i][j] = inf;
                    for h in (j - 1)..i {
                        f[i][j] = f[i][j].min(f[h][j - 1] + g[h][i - 1]);
                    }
                }
            }
        }

        f[n][k as usize]
    }
}
