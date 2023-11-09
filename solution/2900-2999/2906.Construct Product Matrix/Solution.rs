impl Solution {
    pub fn construct_product_matrix(grid: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let modulo: i32 = 12345;
        let n = grid.len();
        let m = grid[0].len();
        let mut p: Vec<Vec<i32>> = vec![vec![0; m]; n];
        let mut suf = 1;

        for i in (0..n).rev() {
            for j in (0..m).rev() {
                p[i][j] = suf;
                suf = (((suf as i64) * (grid[i][j] as i64)) % (modulo as i64)) as i32;
            }
        }

        let mut pre = 1;

        for i in 0..n {
            for j in 0..m {
                p[i][j] = (((p[i][j] as i64) * (pre as i64)) % (modulo as i64)) as i32;
                pre = (((pre as i64) * (grid[i][j] as i64)) % (modulo as i64)) as i32;
            }
        }

        p
    }
}
