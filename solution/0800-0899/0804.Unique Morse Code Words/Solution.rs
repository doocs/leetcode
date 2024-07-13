use std::collections::HashSet;
impl Solution {
    pub fn unique_morse_representations(words: Vec<String>) -> i32 {
        const codes: [&str; 26] = [
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
            "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
            "-.--", "--..",
        ];
        words
            .iter()
            .map(|word| {
                word.as_bytes()
                    .iter()
                    .map(|v| codes[(v - b'a') as usize])
                    .collect::<String>()
            })
            .collect::<HashSet<String>>()
            .len() as i32
    }
}
