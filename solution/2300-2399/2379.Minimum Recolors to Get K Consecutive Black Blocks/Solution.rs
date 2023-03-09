impl Solution {
    pub fn minimum_recolors(blocks: String, k: i32) -> i32 {
        let k = k as usize;
        let s = blocks.as_bytes();
        let n = s.len();
        let mut count = 0;
        for i in 0..k {
            if s[i] == b'B' {
                count += 1;
            }
        }
        let mut ans = k - count;
        for i in k..n {
            if s[i - k] == b'B' {
                count -= 1;
            }
            if s[i] == b'B' {
                count += 1;
            }
            ans = ans.min(k - count);
        }
        ans as i32
    }
}
