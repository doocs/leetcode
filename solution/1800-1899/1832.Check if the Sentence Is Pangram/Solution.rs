impl Solution {
    pub fn check_if_pangram(sentence: String) -> bool {
        let mut mark = 0;
        for c in sentence.as_bytes() {
            mark |= 1 << *c - b'a';
        }
        mark == (1 << 26) - 1
    }
}
