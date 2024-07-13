impl Solution {
    pub fn closet_target(words: Vec<String>, target: String, start_index: i32) -> i32 {
        let start_index = start_index as usize;
        let n = words.len();
        for i in 0..=n >> 1 {
            if words[(start_index - i + n) % n] == target || words[(start_index + i) % n] == target
            {
                return i as i32;
            }
        }
        -1
    }
}
