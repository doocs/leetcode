impl Solution {
    pub fn reformat_number(number: String) -> String {
        let cs: Vec<char> = number.chars().filter(|&c| c != ' ' && c != '-').collect();
        let n = cs.len();
        cs.iter()
            .enumerate()
            .map(|(i, c)| {
                if (i + 1) % 3 == 0 && i < n - 2 || n % 3 == 1 && i == n - 3 {
                    return c.to_string() + &"-";
                }
                c.to_string()
            })
            .collect()
    }
}
