impl Solution {
    pub fn simplify_path(path: String) -> String {
        let mut stk = Vec::new();
        for s in path.split('/') {
            match s {
                "" | "." => continue,
                ".." => {
                    stk.pop();
                }
                _ => stk.push(s),
            }
        }
        "/".to_string() + &stk.join("/")
    }
}
