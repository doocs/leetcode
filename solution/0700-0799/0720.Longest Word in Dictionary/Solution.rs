impl Solution {
    pub fn longest_word(mut words: Vec<String>) -> String {
        words.sort_unstable_by(|a, b| (b.len(), a).cmp(&(a.len(), b)));
        for word in words.iter() {
            let mut is_pass = true;
            for i in 1..=word.len() {
                if !words.contains(&word[..i].to_string()) {
                    is_pass = false;
                    break;
                }
            }
            if is_pass {
                return word.clone();
            }
        }
        String::new()
    }
}
