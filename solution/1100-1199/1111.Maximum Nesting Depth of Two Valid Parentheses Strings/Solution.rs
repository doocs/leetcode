impl Solution {
    pub fn max_depth_after_split(seq: String) -> Vec<i32> {
        let n = seq.len();
        let mut ans = vec![0; n];
        let mut x = 0;
        for (i, c) in seq.bytes().enumerate() {
            if c == b'(' {
                ans[i] = x & 1;
                x += 1;
            } else {
                x -= 1;
                ans[i] = x & 1;
            }
        }
        ans
    }
}
