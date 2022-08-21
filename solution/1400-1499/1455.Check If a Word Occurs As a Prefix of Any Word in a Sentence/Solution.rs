impl Solution {
    pub fn is_prefix_of_word(sentence: String, search_word: String) -> i32 {
        let ss = sentence.split_whitespace().collect::<Vec<&str>>();
        for i in 0..ss.len() {
            if ss[i].starts_with(&search_word) {
                return (i + 1) as i32;
            }
        }
        -1
    }
}
