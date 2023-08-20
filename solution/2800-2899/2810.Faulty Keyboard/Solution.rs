impl Solution {
    pub fn final_string(s: String) -> String {
        let mut t = Vec::new();
        for c in s.chars() {
            if c == 'i' {
                t.reverse();
            } else {
                t.push(c);
            }
        }
        t.into_iter().collect()
    }
}
