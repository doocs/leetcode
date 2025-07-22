impl Solution {
    pub fn max_chunks_to_sorted(arr: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut mx = 0;
        for i in 0..arr.len() {
            mx = mx.max(arr[i]);
            if mx == (i as i32) {
                ans += 1;
            }
        }
        ans
    }
}
