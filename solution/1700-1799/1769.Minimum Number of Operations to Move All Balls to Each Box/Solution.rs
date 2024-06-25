impl Solution {
    pub fn min_operations(boxes: String) -> Vec<i32> {
        let s = boxes.as_bytes();
        let n = s.len();
        let mut left = vec![0; n];
        let mut right = vec![0; n];
        let mut count = 0;
        for i in 1..n {
            if s[i - 1] == b'1' {
                count += 1;
            }
            left[i] = left[i - 1] + count;
        }
        count = 0;
        for i in (0..n - 1).rev() {
            if s[i + 1] == b'1' {
                count += 1;
            }
            right[i] = right[i + 1] + count;
        }
        (0..n).into_iter().map(|i| left[i] + right[i]).collect()
    }
}
