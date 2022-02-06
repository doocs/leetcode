impl Solution {
    pub fn min_number(mut nums: Vec<i32>) -> String {
        nums.sort_by(|a, b| format!("{}{}", a, b).cmp(&format!("{}{}", b, a)));
        nums.iter().map(|num| num.to_string()).collect()
    }
}
