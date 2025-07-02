impl Solution {
    pub fn kth_character(k: i32) -> char {
        let mut word = vec![0];
        while word.len() < k as usize {
            let m = word.len();
            for i in 0..m {
                word.push((word[i] + 1) % 26);
            }
        }
        (b'a' + word[(k - 1) as usize] as u8) as char
    }
}
