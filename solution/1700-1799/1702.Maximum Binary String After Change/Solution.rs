impl Solution {
    pub fn maximum_binary_string(binary: String) -> String {
        if let Some(k) = binary.find('0') {
            let k =
                k +
                binary[k + 1..]
                    .chars()
                    .filter(|&c| c == '0')
                    .count();
            return format!("{}{}{}", "1".repeat(k), "0", "1".repeat(binary.len() - k - 1));
        }
        binary
    }
}
