impl Solution {
    pub fn can_make_arithmetic_progression(mut arr: Vec<i32>) -> bool {
        arr.sort();
        let n = arr.len();
        for i in 2..n {
            if arr[i - 2] - arr[i - 1] != arr[i - 1] - arr[i] {
                return false;
            }
        }
        true
    }
}
