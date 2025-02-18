impl Solution {
    pub fn count_vowels(word: String) -> i64 {
        let n = word.len() as i64;
        word.chars()
            .enumerate()
            .filter(|(_, c)| "aeiou".contains(*c))
            .map(|(i, _)| (i as i64 + 1) * (n - i as i64))
            .sum()
    }
}
