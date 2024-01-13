impl Solution {
    pub fn remove_trailing_zeros(num: String) -> String {
        num.chars()
            .rev()
            .skip_while(|&c| c == '0')
            .collect::<String>()
            .chars()
            .rev()
            .collect::<String>()
    }
}
