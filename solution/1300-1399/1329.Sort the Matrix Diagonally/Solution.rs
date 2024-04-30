impl Solution {
    pub fn diagonal_sort(mut mat: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let m = mat.len();
        let n = mat[0].len();
        let mut g: Vec<Vec<i32>> = vec![vec![]; m + n];
        for i in 0..m {
            for j in 0..n {
                g[m - i + j].push(mat[i][j]);
            }
        }
        for e in &mut g {
            e.sort_by(|a, b| b.cmp(a));
        }
        for i in 0..m {
            for j in 0..n {
                mat[i][j] = g[m - i + j].pop().unwrap();
            }
        }
        mat
    }
}
