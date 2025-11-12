use std::collections::HashSet;

impl Solution {
    pub fn distinct_points(s: String, k: i32) -> i32 {
        let n = s.len();
        let mut f = vec![0; n + 1];
        let mut g = vec![0; n + 1];
        let mut x = 0;
        let mut y = 0;
        let bytes = s.as_bytes();
        for i in 1..=n {
            match bytes[i - 1] as char {
                'U' => y += 1,
                'D' => y -= 1,
                'L' => x -= 1,
                _ => x += 1,
            }
            f[i] = x;
            g[i] = y;
        }
        let mut st = HashSet::new();
        let k = k as usize;
        for i in k..=n {
            let a = f[n] - (f[i] - f[i - k]);
            let b = g[n] - (g[i] - g[i - k]);
            st.insert((a as i64) * (n as i64) + (b as i64));
        }
        st.len() as i32
    }
}
