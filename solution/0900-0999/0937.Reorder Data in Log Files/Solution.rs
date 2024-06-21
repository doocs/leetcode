impl Solution {
    pub fn reorder_log_files(mut logs: Vec<String>) -> Vec<String> {
        logs.sort_by(|s1, s2| {
            let (start1, content1) = s1.split_once(' ').unwrap();
            let (start2, content2) = s2.split_once(' ').unwrap();
            match (
                content1.chars().nth(0).unwrap().is_digit(10),
                content2.chars().nth(0).unwrap().is_digit(10),
            ) {
                (true, true) => std::cmp::Ordering::Equal,
                (true, false) => std::cmp::Ordering::Greater,
                (false, true) => std::cmp::Ordering::Less,
                (false, false) => content1.cmp(&content2).then(start1.cmp(&start2)),
            }
        });
        logs
    }
}
