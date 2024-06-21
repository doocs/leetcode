impl Solution {
    pub fn count_matches(items: Vec<Vec<String>>, rule_key: String, rule_value: String) -> i32 {
        let key = if rule_key == "type" {
            0
        } else if rule_key == "color" {
            1
        } else {
            2
        };
        items.iter().filter(|v| v[key] == rule_value).count() as i32
    }
}
