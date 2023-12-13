impl Solution {
    pub fn compress_string(s: String) -> String {
        let mut cs: Vec<char> = s.chars().collect();
        let mut t = Vec::new();
        let mut i = 0;
        let n = s.len();
        while i < n {
            let mut j = i + 1;
            while j < n && cs[j] == cs[i] {
                j += 1;
            }
            t.push(cs[i]);
            t.extend((j - i).to_string().chars());
            i = j;
        }

        let t = t.into_iter().collect::<String>();
        if s.len() <= t.len() {
            s
        } else {
            t
        }
    }
}
