impl Solution {
    pub fn clear_stars(s: String) -> String {
        let n = s.len();
        let s_bytes = s.as_bytes();
        let mut g: Vec<Vec<usize>> = vec![vec![]; 26];
        let mut rem = vec![false; n];
        let chars: Vec<char> = s.chars().collect();

        for (i, &ch) in chars.iter().enumerate() {
            if ch == '*' {
                rem[i] = true;
                for j in 0..26 {
                    if let Some(idx) = g[j].pop() {
                        rem[idx] = true;
                        break;
                    }
                }
            } else {
                g[(ch as u8 - b'a') as usize].push(i);
            }
        }

        chars
            .into_iter()
            .enumerate()
            .filter_map(|(i, ch)| if !rem[i] { Some(ch) } else { None })
            .collect()
    }
}
