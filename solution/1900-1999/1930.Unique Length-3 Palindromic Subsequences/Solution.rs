impl Solution {
    pub fn count_palindromic_subsequence(s: String) -> i32 {
        let s_bytes = s.as_bytes();
        let mut ans = 0;
        for c in b'a'..=b'z' {
            if let (Some(l), Some(r)) = (
                s_bytes.iter().position(|&ch| ch == c),
                s_bytes.iter().rposition(|&ch| ch == c),
            ) {
                let mut mask = 0u32;
                for i in (l + 1)..r {
                    let j = (s_bytes[i] - b'a') as u32;
                    if (mask >> j & 1) == 0 {
                        mask |= 1 << j;
                        ans += 1;
                    }
                }
            }
        }
        ans
    }
}
