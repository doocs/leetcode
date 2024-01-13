impl Solution {
    pub fn reverse_words(s: String) -> String {
        s.split(' ')
            .filter(|str| str != &"")
            .rev()
            .collect::<Vec<_>>()
            .join("")
    }
}
