impl Solution {
    pub fn get_longest_subsequence(words: Vec<String>, groups: Vec<i32>) -> Vec<String> {
        let mut ans = Vec::new();
        for (i, &g) in groups.iter().enumerate() {
            if i == 0 || g != groups[i - 1] {
                ans.push(words[i].clone());
            }
        }
        ans
    }
}
