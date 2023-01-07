use std::collections::HashSet;
impl Solution {
    pub fn dest_city(paths: Vec<Vec<String>>) -> String {
        let set = paths.iter().map(|v| &v[0]).collect::<HashSet<&String>>();
        for path in paths.iter() {
            if !set.contains(&path[1]) {
                return path[1].clone();
            }
        }
        String::new()
    }
}
