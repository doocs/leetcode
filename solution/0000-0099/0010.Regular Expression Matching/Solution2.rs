impl Solution {
    pub fn is_match(s: String, p: String) -> bool {
        let m = s.len();
        let n = p.len();
        let mut f = vec![vec![false; n + 1]; m + 1];

        f[0][0] = true;

        let s: Vec<char> = s.chars().collect();
        let p: Vec<char> = p.chars().collect();

        for i in 0..=m {
            for j in 1..=n {
                if p[j - 1] == '*' {
                    f[i][j] = f[i][j - 2];
                    if i > 0 && (p[j - 2] == '.' || p[j - 2] == s[i - 1]) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else if i > 0 && (p[j - 1] == '.' || p[j - 1] == s[i - 1]) {
                    f[i][j] = f[i - 1][j - 1];
                }
            }
        }

        f[m][n]
    }
}
