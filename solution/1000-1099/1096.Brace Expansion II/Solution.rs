use std::collections::BTreeSet;

impl Solution {
    pub fn brace_expansion_ii(expression: String) -> Vec<String> {
        fn dfs(exp: String, s: &mut BTreeSet<String>) {
            let Some(j) = exp.find('}') else {
                s.insert(exp);
                return;
            };
            let i = exp[..j].rfind('{').unwrap();
            let a = &exp[..i];
            let c = &exp[j + 1..];
            for b in exp[i + 1..j].split(',') {
                dfs(format!("{}{}{}", a, b, c), s);
            }
        }
        let mut s = BTreeSet::new();
        dfs(expression, &mut s);
        s.into_iter().collect()
    }
}
