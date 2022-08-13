impl Solution {
    pub fn max_chunks_to_sorted(arr: Vec<i32>) -> i32 {
        let mut res = 0;
        let mut max = 0;
        for i in 0..arr.len() {
            max = max.max(arr[i]);
            if max == i as i32 {
                res += 1;
            }
        }
        res
    }
}
