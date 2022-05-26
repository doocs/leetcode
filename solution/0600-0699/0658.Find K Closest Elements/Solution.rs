impl Solution {
    pub fn find_closest_elements(arr: Vec<i32>, k: i32, x: i32) -> Vec<i32> {
        let n = arr.len();
        let mut l = 0;
        let mut r = n;
        while r - l != k as usize {
            if (arr[l] - x).abs() <= (arr[r - 1] - x).abs() {
                r -= 1;
            } else {
                l += 1;
            }
        }
        arr[l..r].to_vec()
    }
}
