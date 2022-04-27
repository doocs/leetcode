impl Solution {
    pub fn are_almost_equal(s1: String, s2: String) -> bool {
        let (s1, s2) = (s1.as_bytes(), s2.as_bytes());
        let n = s1.len();
        let mut indexs = vec![];
        for i in 0..n {
            let (c1, c2) = (s1[i], s2[i]);
            if c1 != c2 {
                indexs.push(i);
                if indexs.len() > 2 {
                    return false;
                }
            }
        }
        let size = indexs.len();
        if size == 2 {
            return s1[indexs[0]] == s2[indexs[1]] && s2[indexs[0]] == s1[indexs[1]];
        }
        size != 1
    }
}
