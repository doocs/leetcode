impl Solution {
    pub fn largest_merge(word1: String, word2: String) -> String {
        let word1 = word1.as_bytes();
        let word2 = word2.as_bytes();
        let m = word1.len();
        let n = word2.len();
        let mut ans = String::new();
        let mut i = 0;
        let mut j = 0;
        while i < m && j < n {
            if word1[i..] > word2[j..] {
                ans.push(word1[i] as char);
                i += 1;
            } else {
                ans.push(word2[j] as char);
                j += 1;
            }
        }
        word1[i..].iter().for_each(|c| ans.push(*c as char));
        word2[j..].iter().for_each(|c| ans.push(*c as char));
        ans
    }
}
