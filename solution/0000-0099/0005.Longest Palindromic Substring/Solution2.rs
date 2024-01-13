impl Solution {
    pub fn is_palindrome(s: &str) -> bool {
        let mut chars = s.chars();
        while let (Some(c1), Some(c2)) = (chars.next(), chars.next_back()) {
            if c1 != c2 {
                return false;
            }
        }
        true
    }

    pub fn longest_palindrome(s: String) -> String {
        let size = s.len();
        let mut ans = &s[..1];
        for i in 0..size - 1 {
            for j in (i + 1..size).rev() {
                if ans.len() > j - i + 1 {
                    break;
                }
                if Solution::is_palindrome(&s[i..=j]) {
                    ans = &s[i..=j];
                }
            }
        }
        return ans.to_string();
    }
}
