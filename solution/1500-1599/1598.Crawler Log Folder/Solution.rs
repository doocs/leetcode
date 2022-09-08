impl Solution {
    pub fn min_operations(logs: Vec<String>) -> i32 {
        let mut depth = 0;
        for log in logs.iter() {
            if log == "../" {
                depth = 0.max(depth - 1);
            } else if log != "./" {
                depth += 1;
            }
        }
        depth
    }
}
