impl Solution {
    #[allow(dead_code)]
    pub fn simplify_path(path: String) -> String {
        let mut s: Vec<&str> = Vec::new();

        // Split the path
        let p_vec = path.split("/").collect::<Vec<&str>>();

        // Traverse the path vector
        for p in p_vec {
            match p {
                // Do nothing for "" or "."
                "" | "." => {
                    continue;
                }
                ".." => {
                    if !s.is_empty() {
                        s.pop();
                    }
                }
                _ => s.push(p),
            }
        }

        "/".to_string() + &s.join("/")
    }
}
