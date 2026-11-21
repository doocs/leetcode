impl Solution {
    pub fn array_strings_are_equal(word1: Vec<String>, word2: Vec<String>) -> bool {
        let (mut i1, mut j1, mut i2, mut j2) = (0, 0, 0, 0);
        while i1 < word1.len() && i2 < word2.len() {
            if word1[i1].as_bytes()[j1] != word2[i2].as_bytes()[j2] {
                return false;
            }
            j1 += 1;
            if j1 == word1[i1].len() {
                i1 += 1;
                j1 = 0;
            }
            j2 += 1;
            if j2 == word2[i2].len() {
                i2 += 1;
                j2 = 0;
            }
        }
        i1 == word1.len() && i2 == word2.len()
    }
}
