impl Solution {
    pub fn shortest_completing_word(license_plate: String, words: Vec<String>) -> String {
        let mut cnt = vec![0; 26];
        for c in license_plate.chars() {
            if c.is_ascii_alphabetic() {
                cnt[((c.to_ascii_lowercase() as u8) - b'a') as usize] += 1;
            }
        }
        let mut ans = String::new();
        for w in words {
            if !ans.is_empty() && w.len() >= ans.len() {
                continue;
            }
            let mut t = vec![0; 26];
            for c in w.chars() {
                t[((c as u8) - b'a') as usize] += 1;
            }
            let mut ok = true;
            for i in 0..26 {
                if t[i] < cnt[i] {
                    ok = false;
                    break;
                }
            }
            if ok {
                ans = w;
            }
        }
        ans
    }
}
