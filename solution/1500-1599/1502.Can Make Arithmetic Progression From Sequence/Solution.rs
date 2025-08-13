impl Solution {
    pub fn can_make_arithmetic_progression(mut arr: Vec<i32>) -> bool {
        arr.sort();
        let n = arr.len();
        let d = arr[1] - arr[0];
        for i in 2..n {
            if arr[i] - arr[i - 1] != d {
                return false;
            }
        }
        true
    }
}
