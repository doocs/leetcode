impl Solution {
    pub fn min_distance(word1: String, word2: String) -> i32 {
        let m = word1.len();
        let n = word2.len();
        let s: Vec<char> = word1.chars().collect();
        let t: Vec<char> = word2.chars().collect();
        let mut f = vec![vec![0; n + 1]; m + 1];

        for i in 0..=m {
            f[i][0] = i as i32;
        }
        for j in 0..=n {
            f[0][j] = j as i32;
        }

        for i in 1..=m {
            for j in 1..=n {
                let a = s[i - 1];
                let b = t[j - 1];
                if a == b {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = std::cmp::min(f[i - 1][j], f[i][j - 1]) + 1;
                }
            }
        }
        f[m][n]
    }
}
