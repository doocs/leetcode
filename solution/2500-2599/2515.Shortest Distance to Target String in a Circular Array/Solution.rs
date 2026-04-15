impl Solution {
    pub fn closest_target(words: Vec<String>, target: String, start_index: i32) -> i32 {
        let n = words.len() as i32;
        let mut ans = n;
        for (i, w) in words.iter().enumerate() {
            if w == &target {
                let t = (i as i32 - start_index).abs();
                ans = ans.min(t.min(n - t));
            }
        }
        if ans == n { -1 } else { ans }
    }
}
