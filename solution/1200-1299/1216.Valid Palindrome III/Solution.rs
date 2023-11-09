impl Solution {
    pub fn is_valid_palindrome(s: String, k: i32) -> bool {
        let s = s.as_bytes();
        let n = s.len();
        let mut f = vec![vec![0; n]; n];

        for i in 0..n {
            f[i][i] = 1;
        }

        for i in (0..n - 2).rev() {
            for j in i + 1..n {
                if s[i] == s[j] {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = std::cmp::max(f[i + 1][j], f[i][j - 1]);
                }

                if f[i][j] + k >= (n as i32) {
                    return true;
                }
            }
        }

        false
    }
}
