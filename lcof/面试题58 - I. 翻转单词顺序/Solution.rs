impl Solution {
    pub fn reverse_words(mut s: String) -> String {
        let mut res = s.trim().split(' ').rev().collect::<Vec<&str>>();
        for i in (0..res.len()).rev() {
            if res[i] == "" {
                res.remove(i);
            }
        }
        res.join(" ")
    }
}
