impl Solution {
    pub fn first_uniq_char(s: String) -> char {
        let mut cnt = [0; 26];
        for c in s.chars() {
            cnt[(c as usize) - ('a' as usize)] += 1;
        }
        for c in s.chars() {
            if cnt[(c as usize) - ('a' as usize)] == 1 {
                return c;
            }
        }
        ' '
    }
}
