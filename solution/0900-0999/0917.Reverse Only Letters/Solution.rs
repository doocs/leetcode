impl Solution {
    pub fn reverse_only_letters(s: String) -> String {
        let mut cs: Vec<char> = s.chars().collect();
        let n = cs.len();
        let mut l = 0;
        let mut r = n - 1;
        while l < r {
            if !cs[l].is_ascii_alphabetic() {
                l += 1;
            } else if !cs[r].is_ascii_alphabetic() {
                r -= 1;
            } else {
                cs.swap(l, r);
                l += 1;
                r -= 1;
            }
        }
        cs.iter().collect()
    }
}
