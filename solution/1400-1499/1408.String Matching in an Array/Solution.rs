impl Solution {
    pub fn string_matching(words: Vec<String>) -> Vec<String> {
        let mut res = Vec::new();
        for target in words.iter() {
            for word in words.iter() {
                if word != target && word.contains(target) {
                    res.push(target.clone());
                    break;
                }
            }
        }
        res
    }
}
