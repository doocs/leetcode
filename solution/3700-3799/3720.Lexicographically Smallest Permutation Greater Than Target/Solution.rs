impl Solution {
    pub fn lex_greater_permutation(s: String, target: String) -> String {
        let mut permutation = s.into_bytes();
        let target_bytes = target.as_bytes();
        let mut letter_counts = [0usize; 26];
        for &byte in &permutation {
            letter_counts[(byte - b'a') as usize] += 1;
        }
        let mut prefix_length = 0;
        while prefix_length < target_bytes.len() {
            let target_letter = (target_bytes[prefix_length] - b'a') as usize;
            if letter_counts[target_letter] == 0 {
                break;
            }
            permutation[prefix_length] = target_bytes[prefix_length];
            letter_counts[target_letter] -= 1;
            prefix_length += 1;
        }
        loop {
            if prefix_length < target_bytes.len() {
                let next_letter = (target_bytes[prefix_length] - b'a') as usize + 1;
                if let Some(replacement_letter) =
                    (next_letter..26).find(|&letter| letter_counts[letter] > 0)
                {
                    permutation[prefix_length] = b'a' + replacement_letter as u8;
                    letter_counts[replacement_letter] -= 1;
                    let mut write_index = prefix_length + 1;
                    for (letter, &count) in letter_counts.iter().enumerate() {
                        for _ in 0..count {
                            permutation[write_index] = b'a' + letter as u8;
                            write_index += 1;
                        }
                    }
                    return String::from_utf8(permutation).unwrap();
                }
            }
            if prefix_length == 0 {
                return String::new();
            }
            prefix_length -= 1;
            letter_counts[(target_bytes[prefix_length] - b'a') as usize] += 1;
        }
    }
}
