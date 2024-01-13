impl Solution {
    pub fn is_circular_sentence(sentence: String) -> bool {
        let n = sentence.len();
        let chars: Vec<char> = sentence.chars().collect();

        if chars[0] != chars[n - 1] {
            return false;
        }

        for i in 1..n - 1 {
            if chars[i] == ' ' && chars[i - 1] != chars[i + 1] {
                return false;
            }
        }

        true
    }
}
