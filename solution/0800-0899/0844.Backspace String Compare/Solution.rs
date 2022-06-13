impl Solution {
    pub fn backspace_compare(s: String, t: String) -> bool {
        let (s, t) = (s.as_bytes(), t.as_bytes());
        let (mut i, mut j) = (s.len(), t.len());
        while i != 0 || j != 0 {
            let mut skip = 0;
            while i != 0 {
                if s[i - 1] == b'#' {
                    skip += 1;
                } else if skip != 0 {
                    skip -= 1;
                } else {
                    break;
                }
                i -= 1
            }
            skip = 0;
            while j != 0 {
                if t[j - 1] == b'#' {
                    skip += 1;
                } else if skip != 0 {
                    skip -= 1;
                } else {
                    break;
                }
                j -= 1
            }
            if i == 0 && j == 0 {
                break;
            }
            if i == 0 || j == 0 {
                return false;
            }
            if s[i - 1] != t[j - 1] {
                return false;
            }
            i -= 1;
            j -= 1;
        }
        true
    }
}
