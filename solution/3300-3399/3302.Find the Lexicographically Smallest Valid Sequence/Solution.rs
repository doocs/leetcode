impl Solution {
    pub fn valid_sequence(word1: String, word2: String) -> Vec<i32> {
        let word1_bytes = word1.as_bytes();
        let word2_bytes = word2.as_bytes();
        let mut positions = vec![-1i32; word2_bytes.len()];
        let mut word2_index = word2_bytes.len() as isize - 1;
        let mut word1_index = word1_bytes.len() as isize - 1;
        while word1_index >= 0 && word2_index >= 0 {
            if word1_bytes[word1_index as usize] == word2_bytes[word2_index as usize] {
                positions[word2_index as usize] = word1_index as i32;
                word2_index -= 1;
            }
            word1_index -= 1;
        }
        let mut mismatch_available = true;
        let mut matched_count = 0usize;
        for (index, &byte) in word1_bytes.iter().enumerate() {
            if matched_count == word2_bytes.len() {
                break;
            }
            if byte == word2_bytes[matched_count] {
                positions[matched_count] = index as i32;
                matched_count += 1;
            } else if mismatch_available
                && (matched_count + 1 == word2_bytes.len()
                    || (index as i32) < positions[matched_count + 1])
            {
                mismatch_available = false;
                positions[matched_count] = index as i32;
                matched_count += 1;
            }
        }
        if matched_count == word2_bytes.len() {
            positions
        } else {
            Vec::new()
        }
    }
}
