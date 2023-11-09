impl Solution {
    pub fn get_row(row_index: i32) -> Vec<i32> {
        let n = (row_index + 1) as usize;
        let mut f = vec![1; n];
        for i in 2..n {
            for j in (1..i).rev() {
                f[j] += f[j - 1];
            }
        }
        f
    }
}
