impl Solution {
    pub fn two_edit_words(queries: Vec<String>, dictionary: Vec<String>) -> Vec<String> {
        queries
            .into_iter()
            .filter(|s| {
                dictionary.iter().any(|t| {
                    s
                        .chars()
                        .zip(t.chars())
                        .filter(|&(a, b)| a != b)
                        .count() < 3
                })
            })
            .collect()
    }
}
