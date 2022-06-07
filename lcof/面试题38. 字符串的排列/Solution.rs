use std::collections::HashSet;
impl Solution {
    fn dfs(i: usize, cs: &mut Vec<char>, res: &mut Vec<String>) {
        if i == cs.len() {
            res.push(cs.iter().collect());
            return;
        }
        let mut set = HashSet::new();
        for j in i..cs.len() {
            if set.contains(&cs[j]) {
                continue;
            }
            set.insert(cs[j]);
            cs.swap(i, j);
            Self::dfs(i + 1, cs, res);
            cs.swap(i, j);
        }
    }

    pub fn permutation(s: String) -> Vec<String> {
        let mut res = Vec::new();
        Self::dfs(0, &mut s.chars().collect(), &mut res);
        res
    }
}
