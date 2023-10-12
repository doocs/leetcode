impl Solution {
    pub fn min_window(s: String, t: String) -> String {
        let (mut need, mut window, mut cnt) = ([0; 256], [0; 256], 0);
        for c in t.chars() {
            need[c as usize] += 1;
        }
        let (mut j, mut k, mut mi) = (0, -1, 1 << 31);
        for (i, c) in s.chars().enumerate() {
            window[c as usize] += 1;
            if need[c as usize] >= window[c as usize] {
                cnt += 1;
            }

            while cnt == t.len() {
                if i - j + 1 < mi {
                    k = j as i32;
                    mi = i - j + 1;
                }
                let l = s.chars().nth(j).unwrap() as usize;
                if need[l] >= window[l] {
                    cnt -= 1;
                }
                window[l] -= 1;
                j += 1;
            }
        }
        if k < 0 {
            return "".to_string();
        }
        let k = k as usize;
        s[k..k + mi].to_string()
    }
}
