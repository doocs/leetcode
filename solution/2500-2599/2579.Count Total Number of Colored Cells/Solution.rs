impl Solution {
    pub fn colored_cells(n: i32) -> i64 {
        2 * (n as i64) * ((n as i64) - 1) + 1
    }
}
