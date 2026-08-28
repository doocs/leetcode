impl Solution {
    pub fn smallest_palindrome(s: String, k: i32) -> String {
        let bytes = s.as_bytes();
        let rank = k.max(1) as usize;
        const COUNT_LIMIT: usize = 1_000_001;
        let mut frequencies = [0usize; 26];
        for &byte in bytes {
            frequencies[(byte - b'a') as usize] += 1;
        }
        let mut odd_count = 0usize;
        let mut middle_char_index = 26u8;
        for char_index in 0..26 {
            if frequencies[char_index] & 1 == 1 {
                odd_count += 1;
                middle_char_index = char_index as u8;
            }
        }
        if odd_count > 1 {
            return String::new();
        }
        let mut half_frequencies = [0usize; 26];
        let mut half_length = 0usize;
        for char_index in 0..26 {
            half_frequencies[char_index] = frequencies[char_index] / 2;
            half_length += half_frequencies[char_index];
        }
        let count_permutations = |counts: &[usize; 26]| -> usize {
            let mut remaining: usize = counts.iter().sum();
            let mut permutations = 1usize;
            for &count in counts {
                if count == 0 {
                    continue;
                }
                let selected = count.min(remaining - count);
                let mut combinations = 1usize;
                for step in 1..=selected {
                    combinations = combinations * (remaining - step + 1) / step;
                    if combinations >= COUNT_LIMIT {
                        combinations = COUNT_LIMIT;
                        break;
                    }
                }
                permutations *= combinations;
                if permutations >= COUNT_LIMIT {
                    return COUNT_LIMIT;
                }
                remaining -= count;
            }
            permutations
        };
        if rank > count_permutations(&half_frequencies) {
            return String::new();
        }
        let length = bytes.len();
        let mut palindrome = vec![0u8; length];
        let mut remaining_rank = rank;
        let mut position = 0usize;
        for _ in 0..half_length {
            for char_index in 0..26 {
                if half_frequencies[char_index] == 0 {
                    continue;
                }
                half_frequencies[char_index] -= 1;
                let suffix_count = count_permutations(&half_frequencies);
                if suffix_count >= remaining_rank {
                    palindrome[position] = char_index as u8 + b'a';
                    position += 1;
                    break;
                }
                remaining_rank -= suffix_count;
                half_frequencies[char_index] += 1;
            }
        }
        if middle_char_index < 26 {
            palindrome[half_length] = middle_char_index + b'a';
        }
        for index in 0..half_length {
            palindrome[length - 1 - index] = palindrome[index];
        }
        String::from_utf8(palindrome).unwrap()
    }
}
