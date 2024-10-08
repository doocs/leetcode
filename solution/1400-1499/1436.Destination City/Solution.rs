use std::collections::HashSet;

impl Solution {
    pub fn dest_city(paths: Vec<Vec<String>>) -> String {
        let s = paths
            .iter()
            .map(|p| p[0].clone())
            .collect::<HashSet<String>>();
        paths.into_iter().find(|p| !s.contains(&p[1])).unwrap()[1].clone()
    }
}
