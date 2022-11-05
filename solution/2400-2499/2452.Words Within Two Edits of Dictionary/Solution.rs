impl Solution {
    pub fn two_edit_words(queries: Vec<String>, dictionary: Vec<String>) -> Vec<String> {
        let n = queries[0].len();
        queries
            .into_iter()
            .filter(|querie| {
                for s in dictionary.iter() {
                    let mut diff = 0;
                    for i in 0..n {
                        if querie.as_bytes()[i] != s.as_bytes()[i] {
                            diff += 1;
                        }
                    }
                    if diff <= 2 {
                        return true;
                    }
                }
                false
            })
            .collect()
    }
}
