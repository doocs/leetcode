impl Solution {
    pub fn longest_palindrome(word1: String, word2: String) -> i32 {
        let s: Vec<char> = format!("{}{}", word1, word2).chars().collect();
        let n = s.len();
        let mut f = vec![vec![0; n]; n];
        for i in 0..n {
            f[i][i] = 1;
        }
        let mut ans = 0;
        for i in (0..n - 1).rev() {
            for j in i + 1..n {
                if s[i] == s[j] {
                    f[i][j] = f[i + 1][j - 1] + 2;
                    if i < word1.len() && j >= word1.len() {
                        ans = ans.max(f[i][j]);
                    }
                } else {
                    f[i][j] = f[i + 1][j].max(f[i][j - 1]);
                }
            }
        }
        ans
    }
}
