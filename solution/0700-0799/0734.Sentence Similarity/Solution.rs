use std::collections::HashSet;

impl Solution {
    pub fn are_sentences_similar(
        sentence1: Vec<String>,
        sentence2: Vec<String>,
        similar_pairs: Vec<Vec<String>>,
    ) -> bool {
        if sentence1.len() != sentence2.len() {
            return false;
        }

        let s: HashSet<(String, String)> = similar_pairs
            .into_iter()
            .map(|pair| (pair[0].clone(), pair[1].clone()))
            .collect();

        for (x, y) in sentence1.iter().zip(sentence2.iter()) {
            if x != y
                && !s.contains(&(x.clone(), y.clone()))
                && !s.contains(&(y.clone(), x.clone()))
            {
                return false;
            }
        }
        true
    }
}
