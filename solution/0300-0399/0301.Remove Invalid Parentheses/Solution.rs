use std::collections::HashSet;

impl Solution {
    pub fn remove_invalid_parentheses(s: String) -> Vec<String> {
        let s = s.into_bytes();
        let mut l = 0;
        let mut r = 0;
        for &c in &s {
            if c == b'(' {
                l += 1;
            } else if c == b')' {
                if l > 0 {
                    l -= 1;
                } else {
                    r += 1;
                }
            }
        }
        let mut ans = HashSet::new();

        fn dfs(
            s: &[u8],
            i: usize,
            l: usize,
            r: usize,
            lcnt: usize,
            rcnt: usize,
            t: &mut Vec<u8>,
            ans: &mut HashSet<String>,
        ) {
            let n = s.len();
            if i == n {
                if l == 0 && r == 0 {
                    ans.insert(String::from_utf8(t.clone()).unwrap());
                }
                return;
            }
            if n - i < l + r || lcnt < rcnt {
                return;
            }
            let c = s[i];
            if c == b'(' && l > 0 {
                dfs(s, i + 1, l - 1, r, lcnt, rcnt, t, ans);
            }
            if c == b')' && r > 0 {
                dfs(s, i + 1, l, r - 1, lcnt, rcnt, t, ans);
            }
            let x = if c == b'(' { 1 } else { 0 };
            let y = if c == b')' { 1 } else { 0 };
            t.push(c);
            dfs(s, i + 1, l, r, lcnt + x, rcnt + y, t, ans);
            t.pop();
        }

        dfs(&s, 0, l, r, 0, 0, &mut Vec::new(), &mut ans);
        ans.into_iter().collect()
    }
}
