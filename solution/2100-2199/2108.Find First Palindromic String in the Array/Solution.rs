impl Solution {
    pub fn first_palindrome(words: Vec<String>) -> String {
        for word in words.iter() {
            let s = word.as_bytes();
            let mut left = 0;
            let mut right = s.len() - 1;
            while (left < right) {
                if (s[left] != s[right]) {
                    break;
                }
                left += 1;
                right -= 1;
            }
            if left >= right {
                return word.clone();
            }
        }
        String::new()
    }
}
