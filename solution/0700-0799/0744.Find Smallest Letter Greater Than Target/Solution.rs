impl Solution {
    pub fn next_greatest_letter(letters: Vec<char>, target: char) -> char {
        *letters
            .iter()
            .find(|&&c| c > target)
            .unwrap_or(&letters[0])
    }
}
