impl Solution {
    pub fn minimum_delete_sum(s1: String, s2: String) -> i32 {
        let m: usize = s1.len();
        let n: usize = s2.len();
        let b1 = s1.as_bytes();
        let b2 = s2.as_bytes();

        let mut f: Vec<Vec<i32>> = vec![vec![0; n + 1]; m + 1];

        for i in 1..=m {
            f[i][0] = f[i - 1][0] + b1[i - 1] as i32;
        }
        for j in 1..=n {
            f[0][j] = f[0][j - 1] + b2[j - 1] as i32;
        }

        for i in 1..=m {
            for j in 1..=n {
                if b1[i - 1] == b2[j - 1] {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = std::cmp::min(
                        f[i - 1][j] + b1[i - 1] as i32,
                        f[i][j - 1] + b2[j - 1] as i32,
                    );
                }
            }
        }

        f[m][n]
    }
}
