impl Solution {
    fn dfs(i: usize, cs: &mut Vec<char>, res: &mut Vec<String>) {
        if i == cs.len() {
            res.push(cs.iter().collect());
            return;
        }
        Self::dfs(i + 1, cs, res);
        if cs[i] >= 'A' {
            cs[i] = char::from((cs[i] as u8) ^ 32);
            Self::dfs(i + 1, cs, res);
        }
    }

    pub fn letter_case_permutation(s: String) -> Vec<String> {
        let mut res = Vec::new();
        let mut cs = s.chars().collect::<Vec<char>>();
        Self::dfs(0, &mut cs, &mut res);
        res
    }
}
