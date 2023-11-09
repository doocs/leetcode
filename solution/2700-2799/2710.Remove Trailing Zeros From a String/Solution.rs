impl Solution {
    pub fn remove_trailing_zeros(num: String) -> String {
        let mut i = num.len() - 1;

        while num.chars().nth(i) == Some('0') {
            i -= 1;
        }

        num[..i + 1].to_string()
    }
}
