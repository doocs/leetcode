use std::collections::HashSet;

const BASE: u64 = 131;

impl Solution {
    #[allow(dead_code)]
    pub fn distinct_echo_substrings(text: String) -> i32 {
        let n = text.len();
        let mut vis: HashSet<u64> = HashSet::new();
        let mut base_vec: Vec<u64> = vec![1; n + 1];
        let mut hash_vec: Vec<u64> = vec![0; n + 1];

        // Initialize the base vector & hash vector
        for i in 0..n {
            let cur_char = ((text.chars().nth(i).unwrap() as u8) - ('a' as u8) + 1) as u64;
            // Update base vector
            base_vec[i + 1] = base_vec[i] * BASE;
            // Update hash vector
            hash_vec[i + 1] = hash_vec[i] * BASE + cur_char;
        }

        // Traverse the text to find the result pair, using rolling hash
        for i in 0..n - 1 {
            for j in i + 1..n {
                // Prevent overflow
                let k = i + (j - i) / 2;
                let left = Self::get_hash(i + 1, k + 1, &base_vec, &hash_vec);
                let right = Self::get_hash(k + 2, j + 1, &base_vec, &hash_vec);
                if left == right {
                    vis.insert(left);
                }
            }
        }

        vis.len() as i32
    }

    #[allow(dead_code)]
    fn get_hash(start: usize, end: usize, base_vec: &Vec<u64>, hash_vec: &Vec<u64>) -> u64 {
        hash_vec[end] - hash_vec[start - 1] * base_vec[end - start + 1]
    }
}
