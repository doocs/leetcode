impl Solution {
    pub fn generate(num_rows: i32) -> Vec<Vec<i32>> {
        let mut f = vec![vec![1]];
        for i in 1..num_rows {
            let mut g = vec![1];
            for j in 1..f[i as usize - 1].len() {
                g.push(f[i as usize - 1][j - 1] + f[i as usize - 1][j]);
            }
            g.push(1);
            f.push(g);
        }
        f
    }
}
