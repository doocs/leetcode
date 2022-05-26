impl Solution {
    pub fn one_edit_away(first: String, second: String) -> bool {
        let (f_len, s_len) = (first.len(), second.len());
        let (first, second) = (first.as_bytes(), second.as_bytes());
        let (mut i, mut j) = (0, 0);
        let mut count = 0;
        while i < f_len && j < s_len {
            if first[i] != second[j] {
                if count > 0 {
                    return false;
                }

                count += 1;
                if i + 1 < f_len && first[i + 1] == second[j] {
                    i += 1;
                } else if j + 1 < s_len && first[i] == second[j + 1] {
                    j += 1;
                }
            }
            i += 1;
            j += 1;
        }
        count += f_len - i + s_len - j;
        count <= 1
    }
}
