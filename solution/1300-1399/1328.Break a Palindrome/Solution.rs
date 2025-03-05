impl Solution {
    pub fn break_palindrome(palindrome: String) -> String {
        let n = palindrome.len();
        if n == 1 {
            return "".to_string();
        }
        let mut s: Vec<char> = palindrome.chars().collect();
        let mut i = 0;

        while i < n / 2 && s[i] == 'a' {
            i += 1;
        }

        if i == n / 2 {
            s[n - 1] = 'b';
        } else {
            s[i] = 'a';
        }

        s.into_iter().collect()
    }
}
