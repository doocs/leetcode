impl Solution {
    pub fn remove_palindrome_sub(s: String) -> i32 {
        let mut l = 0;
        let mut r = s.len() - 1;
        let s: Vec<char> = s.chars().collect();
        while l < r {
            if s[l] != s[r] {
                return 2;
            }
            l += 1;
            r -= 1;
        }
        1
    }
}
