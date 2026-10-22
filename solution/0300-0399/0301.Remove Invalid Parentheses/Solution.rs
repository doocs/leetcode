impl Solution {
    pub fn remove_invalid_parentheses(s: String) -> Vec<String> {
        fn dfs(
            buf: &mut [u8],
            i: usize,
            len: usize,
            l: usize,
            r: usize,
            balance: usize,
            prev_removed: bool,
            ans: &mut Vec<String>,
        ) {
            if buf.len() - i < l + r + balance {
                return;
            }
            if i == buf.len() {
                let t = std::str::from_utf8(&buf[..len]).unwrap();
                if !ans.iter().any(|s| s == t) {
                    ans.push(t.to_owned());
                }
                return;
            }
            let c = buf[i];
            if prev_removed || i == 0 || c != buf[i - 1] {
                match c {
                    b'(' if l > 0 => dfs(buf, i + 1, len, l - 1, r, balance, true, ans),
                    b')' if r > 0 => dfs(buf, i + 1, len, l, r - 1, balance, true, ans),
                    _ => {}
                }
            }
            if c == b')' && balance == 0 {
                return;
            }
            let saved = buf[len];
            buf[len] = c;
            dfs(
                buf,
                i + 1,
                len + 1,
                l,
                r,
                match c {
                    b'(' => balance + 1,
                    b')' => balance - 1,
                    _ => balance,
                },
                false,
                ans,
            );
            buf[len] = saved;
        }
        let (l, r) = s.bytes().fold((0usize, 0usize), |(l, r), c| match c {
            b'(' => (l + 1, r),
            b')' if l > 0 => (l - 1, r),
            b')' => (l, r + 1),
            _ => (l, r),
        });
        if l == 0 && r == 0 {
            return vec![s];
        }
        let mut buf = s.into_bytes();
        let mut ans = Vec::new();
        dfs(&mut buf, 0, 0, l, r, 0, false, &mut ans);
        ans
    }
}
