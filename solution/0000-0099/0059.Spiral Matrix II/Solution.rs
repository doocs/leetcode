impl Solution {
    pub fn generate_matrix(n: i32) -> Vec<Vec<i32>> {
        let n = n as usize;
        let mut res = vec![vec![0; n]; n];
        let mut num = 1;
        for i in 0..n / 2 {
            for j in i..n - i - 1 {
                res[i][j] = num;
                num += 1;
            }
            for j in i..n - i - 1 {
                res[j][n - i - 1] = num;
                num += 1;
            }
            for j in i..n - i - 1 {
                res[n - i - 1][n - j - 1] = num;
                num += 1;
            }
            for j in i..n - i - 1 {
                res[n - j - 1][i] = num;
                num += 1;
            }
        }
        if n % 2 == 1 {
            res[n >> 1][n >> 1] = num;
        }
        res
    }
}
