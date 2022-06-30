impl Solution {
    pub fn longest_palindrome(s: String) -> String {
        let n = s.len();
        let s = s.as_bytes();
        let is_pass = |mut l, mut r| {
            while l < r {
                if s[l] != s[r] {
                    return false;
                }
                l += 1;
                r -= 1;
            }
            true
        };
        let mut res = &s[0..1];
        for i in 0..n - 1 {
            for j in (i + 1..n).rev() {
                if res.len() > j - i {
                    break;
                }
                if is_pass(i, j) {
                    res = &s[i..=j];
                }
            }
        }
        res.into_iter().map(|c| char::from(*c)).collect()
    }
}
