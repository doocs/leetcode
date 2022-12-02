impl Solution {
    pub fn min_operations(boxes: String) -> Vec<i32> {
        let s = boxes.as_bytes();
        let n = s.len();
        let mut ans = vec![0; n];
        let mut count = 0;
        for i in 1..n {
            if s[i - 1] == b'1' {
                count += 1;
            }
            ans[i] = ans[i - 1] + count;
        }
        let mut sum = 0;
        count = 0;
        for i in (0..n - 1).rev() {
            if s[i + 1] == b'1' {
                count += 1;
            }
            sum += count;
            ans[i] += sum;
        }
        ans
    }
}
