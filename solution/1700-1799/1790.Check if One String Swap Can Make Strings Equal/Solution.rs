impl Solution {
    pub fn are_almost_equal(s1: String, s2: String) -> bool {
        if s1 == s2 {
            return true;
        }
        let (s1, s2) = (s1.as_bytes(), s2.as_bytes());
        let mut idxs = vec![];
        for i in 0..s1.len() {
            if s1[i] != s2[i] {
                idxs.push(i);
            }
        }
        if idxs.len() != 2 {
            return false;
        }
        s1[idxs[0]] == s2[idxs[1]] && s2[idxs[0]] == s1[idxs[1]]
    }
}
