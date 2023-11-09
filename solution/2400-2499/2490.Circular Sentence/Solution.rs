impl Solution {
    pub fn is_circular_sentence(sentence: String) -> bool {
        let ss: Vec<String> = sentence.split(' ').map(String::from).collect();
        let n = ss.len();
        for i in 0..n {
            if ss[i].as_bytes()[ss[i].len() - 1] != ss[(i + 1) % n].as_bytes()[0] {
                return false;
            }
        }
        return true;
    }
}
