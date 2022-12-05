impl Solution {
    pub fn is_circular_sentence(sentence: String) -> bool {
        let ss: Vec<String> = sentence.split(' ').map(String::from).collect();
        let n = ss.len();
        if ss[0].as_bytes()[0] != ss[n - 1].as_bytes()[ss[n - 1].len() - 1] {
            return false;
        }
        for i in 1..n {
            if ss[i - 1].as_bytes()[ss[i - 1].len() - 1] != ss[i].as_bytes()[0] {
                return false;
            }
        }
        return true;
    }
}
