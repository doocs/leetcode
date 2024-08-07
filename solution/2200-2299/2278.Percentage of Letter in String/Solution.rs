impl Solution {
    pub fn percentage_letter(s: String, letter: char) -> i32 {
        let count = s.chars().filter(|&c| c == letter).count();
        (100 * count as i32 / s.len() as i32) as i32
    }
}
