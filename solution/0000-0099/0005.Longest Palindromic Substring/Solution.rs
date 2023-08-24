impl Solution {
    pub fn longest_palindrome(s: String) -> String {
        let (n, mut ans) = (s.len(), &s[..1]);
        let mut dp = vec![vec![false; n]; n];
        let data: Vec<char> = s.chars().collect();

        for end in 1..n {
            for start in 0..=end {
                if data[start] == data[end] {
                    dp[start][end] = end - start < 2 || dp[start + 1][end - 1];
                    if dp[start][end] && (end - start + 1) > ans.len() {
                        ans = &s[start..=end];
                    }
                }
            }
        }
        ans.to_string()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn palindrome() {
        assert_eq!(Solution::longest_palindrome(String::from("aba")), String::from("aba"));
        assert_eq!(Solution::longest_palindrome(String::from("babcd")), String::from("bab"));
        assert_eq!(Solution::longest_palindrome(String::from("cbbd")), String::from("bb"));
        assert_eq!(Solution::longest_palindrome(String::from("cbbc")), String::from("cbbc"));
    }
}