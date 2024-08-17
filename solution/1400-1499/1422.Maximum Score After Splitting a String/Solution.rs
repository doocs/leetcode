impl Solution {
    pub fn max_score(s: String) -> i32 {
        let mut l = 0;
        let mut r = s.bytes().filter(|&b| b == b'1').count() as i32;
        let mut ans = 0;
        let cs = s.as_bytes();
        for i in 0..s.len() - 1 {
            l += ((cs[i] - b'0') ^ 1) as i32;
            r -= (cs[i] - b'0') as i32;
            ans = ans.max(l + r);
        }
        ans
    }
}
