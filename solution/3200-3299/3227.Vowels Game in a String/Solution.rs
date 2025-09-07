impl Solution {
    pub fn does_alice_win(s: String) -> bool {
        let vowels = "aeiou";
        for c in s.chars() {
            if vowels.contains(c) {
                return true;
            }
        }
        false
    }
}
