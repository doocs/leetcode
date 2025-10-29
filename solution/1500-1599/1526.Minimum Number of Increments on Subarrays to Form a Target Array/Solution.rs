impl Solution {
    pub fn min_number_operations(target: Vec<i32>) -> i32 {
        let mut f = target[0];
        for i in 1..target.len() {
            if target[i] > target[i - 1] {
                f += target[i] - target[i - 1];
            }
        }
        f
    }
}
