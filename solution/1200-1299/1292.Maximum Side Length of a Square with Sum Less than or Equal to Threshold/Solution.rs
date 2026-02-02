impl Solution {
    pub fn max_side_length(mat: Vec<Vec<i32>>, threshold: i32) -> i32 {
        let m = mat.len();
        let n = mat[0].len();
        let mut s = vec![vec![0; n + 1]; m + 1];
        for i in 1..=m {
            for j in 1..=n {
                s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        let check = |k: usize| -> bool {
            for i in 0..=m - k {
                for j in 0..=n - k {
                    if s[i + k][j + k] - s[i][j + k] - s[i + k][j] + s[i][j] <= threshold {
                        return true;
                    }
                }
            }
            false
        };
        let mut l = 0usize;
        let mut r = m.min(n);
        while l < r {
            let mid = (l + r + 1) >> 1;
            if check(mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        l as i32
    }
}
