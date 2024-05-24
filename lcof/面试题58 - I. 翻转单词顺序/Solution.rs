impl Solution {
    pub fn reverse_words(s: String) -> String {
        let mut words = Vec::new();
        let s: Vec<char> = s.chars().collect();
        let mut i = 0;
        let n = s.len();

        while i < n {
            while i < n && s[i] == ' ' {
                i += 1;
            }
            if i < n {
                let mut j = i;
                while j < n && s[j] != ' ' {
                    j += 1;
                }
                words.push(s[i..j].iter().collect::<String>());
                i = j;
            }
        }

        words.reverse();
        words.join(" ")
    }
}
