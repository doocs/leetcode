impl Solution {
    pub fn get_words_in_longest_subsequence(n: i32, words: Vec<String>, groups: Vec<i32>) -> Vec<String> {
        let mut ans = vec![];

        for i in 0..n {
            if i == 0 || groups[i as usize] != groups[(i - 1) as usize] {
                ans.push(words[i as usize].clone());
            }
        }

        ans
    }
}