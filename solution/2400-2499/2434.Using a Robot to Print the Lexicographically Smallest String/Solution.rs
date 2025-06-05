impl Solution {
    pub fn robot_with_string(s: String) -> String {
        let mut cnt = [0; 26];
        for &c in s.as_bytes() {
            cnt[(c - b'a') as usize] += 1;
        }

        let mut ans = Vec::with_capacity(s.len());
        let mut stk = Vec::new();
        let mut mi = 0;

        for &c in s.as_bytes() {
            cnt[(c - b'a') as usize] -= 1;
            while mi < 26 && cnt[mi] == 0 {
                mi += 1;
            }
            stk.push(c);
            while let Some(&top) = stk.last() {
                if (top - b'a') as usize <= mi {
                    ans.push(stk.pop().unwrap());
                } else {
                    break;
                }
            }
        }

        String::from_utf8(ans).unwrap()
    }
}
