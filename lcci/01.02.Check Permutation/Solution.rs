impl Solution {
    pub fn check_permutation(s1: String, s2: String) -> bool {
        let mut v1: Vec<char> = s1.chars().collect();
        let mut v2: Vec<char> = s2.chars().collect();
        v1.sort();
        v2.sort();
        v1 == v2
    }
}
