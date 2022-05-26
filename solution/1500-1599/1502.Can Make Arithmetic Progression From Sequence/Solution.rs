impl Solution {
    pub fn can_make_arithmetic_progression(mut arr: Vec<i32>) -> bool {
        arr.sort();
        let n = arr.len();
        let target = arr[0] - arr[1];
        for i in 2..n {
            if arr[i - 1] - arr[i] != target {
                return false;
            }
        }
        true
    }
}
