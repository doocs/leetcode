impl Solution {
    pub fn get_row(row_index: i32) -> Vec<i32> {
        let n = (row_index + 1) as usize;
        let mut res = vec![1; n];
        for i in 2..n {
            for j in (1..i).rev() {
                res[j] += res[j - 1];
            }
        }
        res
    }
}
