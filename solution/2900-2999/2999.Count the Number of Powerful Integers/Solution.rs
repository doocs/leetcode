impl Solution {
    pub fn number_of_powerful_int(start: i64, finish: i64, limit: i32, s: String) -> i64 {
        fn count(x: i64, limit: i32, s: &str) -> i64 {
            let t = x.to_string();
            if t.len() < s.len() {
                return 0;
            }

            let t_bytes: Vec<u8> = t.bytes().collect();
            let mut f = [-1_i64; 20];

            fn dfs(pos: usize, lim: bool, t: &[u8], s: &str, limit: i32, f: &mut [i64; 20]) -> i64 {
                if t.len() < s.len() {
                    return 0;
                }

                if !lim && f[pos] != -1 {
                    return f[pos];
                }

                if t.len() - pos == s.len() {
                    if lim {
                        let suffix = &t[pos..];
                        let suffix_str = String::from_utf8_lossy(suffix);
                        return if suffix_str.as_ref() >= s { 1 } else { 0 };
                    } else {
                        return 1;
                    }
                }

                let mut ans = 0;
                let up = if lim {
                    (t[pos] - b'0').min(limit as u8)
                } else {
                    limit as u8
                };

                for i in 0..=up {
                    let next_lim = lim && i == t[pos] - b'0';
                    ans += dfs(pos + 1, next_lim, t, s, limit, f);
                }

                if !lim {
                    f[pos] = ans;
                }

                ans
            }

            dfs(0, true, &t_bytes, s, limit, &mut f)
        }

        let a = count(start - 1, limit, &s);
        let b = count(finish, limit, &s);
        b - a
    }
}
