impl Solution {
    pub fn longest_continuous_substring(s: String) -> i32 {
        let s = s.as_bytes();
        let n = s.len();
        let mut res = 1;
        let mut i = 0;
        for j in 1..n {
            if s[j] - s[j - 1] != 1 {
                res = res.max(j - i);
                i = j;
            }
        }
        res.max(n - i) as i32
    }
}
