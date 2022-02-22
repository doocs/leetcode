impl Solution {
    pub fn reverse_string(s: &mut Vec<char>) {
        let n = s.len();
        let mut l = 0;
        let mut r = n - 1;
        while l < r {
            s.swap(l, r);
            l += 1;
            r -= 1;
        }
    }
}
