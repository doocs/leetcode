use std::collections::HashMap;

impl Solution {
    pub fn min_window(s: String, t: String) -> String {
        let mut need: HashMap<char, usize> = HashMap::new();
        let mut window: HashMap<char, usize> = HashMap::new();
        for c in t.chars() {
            *need.entry(c).or_insert(0) += 1;
        }
        let m = s.len();
        let n = t.len();
        let mut k = -1;
        let mut mi = m + 1;
        let mut cnt = 0;

        let s_bytes = s.as_bytes();
        let mut l = 0;
        for r in 0..m {
            let c = s_bytes[r] as char;
            *window.entry(c).or_insert(0) += 1;
            if window[&c] <= *need.get(&c).unwrap_or(&0) {
                cnt += 1;
            }
            while cnt == n {
                if r - l + 1 < mi {
                    mi = r - l + 1;
                    k = l as i32;
                }

                let c = s_bytes[l] as char;
                if window[&c] <= *need.get(&c).unwrap_or(&0) {
                    cnt -= 1;
                }
                *window.entry(c).or_insert(0) -= 1;
                l += 1;
            }
        }
        if k < 0 {
            return String::new();
        }
        s[k as usize..(k as usize + mi)].to_string()
    }
}
