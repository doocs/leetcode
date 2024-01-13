impl Solution {
    pub fn is_palindrome(x: i32) -> bool {
        if x < 0 {
            return false;
        }
        let s = x.to_string();
        let bs = s.as_bytes();
        let n = bs.len();
        let mut l = 0;
        let mut r = n - 1;
        while l < r {
            if bs[l] != bs[r] {
                return false;
            }
            l += 1;
            r -= 1;
        }
        true
    }
}
