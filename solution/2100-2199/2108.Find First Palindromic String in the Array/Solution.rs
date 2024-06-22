impl Solution {
    pub fn first_palindrome(words: Vec<String>) -> String {
        for w in words {
            if w == w.chars().rev().collect::<String>() {
                return w;
            }
        }
        String::new()
    }
}
