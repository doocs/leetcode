impl Solution {
    pub fn reverse_words(mut s: String) -> String {
        s.split_whitespace().rev().collect::<Vec<_>>().join(" ")
    }
}
