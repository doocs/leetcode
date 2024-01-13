impl Solution {
    pub fn count_seniors(details: Vec<String>) -> i32 {
        details
            .iter()
            .filter_map(|s| s[11..13].parse::<i32>().ok())
            .filter(|&age| age > 60)
            .count() as i32
    }
}
