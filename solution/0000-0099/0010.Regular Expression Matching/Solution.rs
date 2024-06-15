impl Solution {
    pub fn is_match(s: String, p: String) -> bool {
        let (m, n) = (s.len(), p.len());
        let mut f = vec![vec![0; n + 1]; m + 1];

        fn dfs(
            s: &Vec<char>,
            p: &Vec<char>,
            f: &mut Vec<Vec<i32>>,
            i: usize,
            j: usize,
            m: usize,
            n: usize
        ) -> bool {
            if j >= n {
                return i == m;
            }
            if f[i][j] != 0 {
                return f[i][j] == 1;
            }
            let mut res = -1;
            if j + 1 < n && p[j + 1] == '*' {
                if
                    dfs(s, p, f, i, j + 2, m, n) ||
                    (i < m && (s[i] == p[j] || p[j] == '.') && dfs(s, p, f, i + 1, j, m, n))
                {
                    res = 1;
                }
            } else if i < m && (s[i] == p[j] || p[j] == '.') && dfs(s, p, f, i + 1, j + 1, m, n) {
                res = 1;
            }
            f[i][j] = res;
            res == 1
        }

        dfs(&s.chars().collect(), &p.chars().collect(), &mut f, 0, 0, m, n)
    }
}
