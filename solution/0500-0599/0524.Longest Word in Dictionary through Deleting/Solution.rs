impl Solution {
    pub fn find_longest_word(s: String, mut dictionary: Vec<String>) -> String {
        dictionary.sort_unstable_by(|a, b| (b.len(), a).cmp(&(a.len(), b)));
        for target in dictionary {
            let target: Vec<char> = target.chars().collect();
            let n = target.len();
            let mut i = 0;
            for c in s.chars() {
                if i == n {
                    break;
                }
                if c == target[i] {
                    i += 1;
                }
            }
            if i == n {
                return target.iter().collect();
            }
        }
        String::new()
    }
}
