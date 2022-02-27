impl Solution {
    pub fn optimal_division(nums: Vec<i32>) -> String {
        let n = nums.len();
        match n {
            1 => nums[0].to_string(),
            2 => nums[0].to_string() + "/" + &nums[1].to_string(),
            _ => {
                let mut res = nums[0].to_string();
                res.push_str("/(");
                for i in 1..n {
                    res.push_str(&nums[i].to_string());
                    res.push('/');
                }
                res.pop();
                res.push(')');
                res
            }
        }
    }
}
