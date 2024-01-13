use std::cmp::min;

impl Solution {
    pub fn closet_target(words: Vec<String>, target: String, start_index: i32) -> i32 {
        let mut ans = words.len();

        for (i, w) in words.iter().enumerate() {
            if *w == target {
                let t = ((i as i32) - start_index).abs();
                ans = min(ans, min(t as usize, words.len() - (t as usize)));
            }
        }

        if ans == words.len() {
            return -1;
        }

        ans as i32
    }
}
