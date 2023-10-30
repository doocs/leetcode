impl Solution {
    pub fn h_index(citations: Vec<i32>) -> i32 {
        let n = citations.len();
        let (mut left, mut right) = (0, n);
        while left < right {
            let mid = ((left + right + 1) >> 1) as usize;
            if citations[n - mid] >= mid as i32 {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        left as i32
    }
}
