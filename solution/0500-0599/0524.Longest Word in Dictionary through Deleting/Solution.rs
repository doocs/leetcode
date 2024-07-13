impl Solution {
    pub fn find_longest_word(s: String, dictionary: Vec<String>) -> String {
        let mut ans = String::new();
        for t in dictionary {
            let a = ans.len();
            let b = t.len();
            if Self::check(&t, &s) && (a < b || (a == b && t < ans)) {
                ans = t;
            }
        }
        ans
    }

    fn check(s: &str, t: &str) -> bool {
        let (m, n) = (s.len(), t.len());
        let mut i = 0;
        let mut j = 0;
        let s: Vec<char> = s.chars().collect();
        let t: Vec<char> = t.chars().collect();

        while i < m && j < n {
            if s[i] == t[j] {
                i += 1;
            }
            j += 1;
        }
        i == m
    }
}
