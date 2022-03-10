impl Solution {
    pub fn one_edit_away(first: String, second: String) -> bool {
        let f_len = first.len();
        let s_len = second.len();
        let first: Vec<char> = first.chars().collect();
        let second: Vec<char> = second.chars().collect();
        match f_len.max(s_len) - f_len.min(s_len) {
            0 => {
                let mut diff = false;
                for i in 0..f_len {
                    if first[i] != second[i] {
                        if diff {
                            return false;
                        } else {
                            diff = true;
                        }
                    }
                }
                true
            }
            1 => {
                let mut diff = false;
                let mut i = 0;
                let mut j = 0;
                while i < f_len && j < s_len {
                    if first[i] != second[j] {
                        if diff {
                            return false;
                        } else {
                            diff = true;
                            if i + 1 != f_len && first[i + 1] == second[j] {
                                i += 1;
                            } else if j + 1 != s_len && first[i] == second[j + 1] {
                                j += 1;
                            } else {
                                return false;
                            }
                        }
                    }
                    i += 1;
                    j += 1;
                }
                if diff && (i != f_len || j != s_len) {
                    return false;
                }
                true
            }
            _ => false,
        }
    }
}
