impl Solution {
    pub fn is_valid(word: String) -> bool {
        if word.len() < 3 {
            return false;
        }

        let mut has_vowel = false;
        let mut has_consonant = false;
        let vowels = ['a', 'e', 'i', 'o', 'u'];

        for c in word.chars() {
            if !c.is_alphanumeric() {
                return false;
            }
            if c.is_alphabetic() {
                let lower_c = c.to_ascii_lowercase();
                if vowels.contains(&lower_c) {
                    has_vowel = true;
                } else {
                    has_consonant = true;
                }
            }
        }

        has_vowel && has_consonant
    }
}
