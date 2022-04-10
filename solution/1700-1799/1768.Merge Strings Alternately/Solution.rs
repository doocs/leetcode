impl Solution {
    pub fn merge_alternately(word1: String, word2: String) -> String {
        let s1 = word1.as_bytes();
        let s2 = word2.as_bytes();
        let n = s1.len().max(s2.len());
        let mut res = vec![];
        for i in 0..n {
            if s1.get(i).is_some() {
                res.push(s1[i]);
            }
            if s2.get(i).is_some() {
                res.push(s2[i]);
            }
        }
        String::from_utf8(res).unwrap()
    }
}
