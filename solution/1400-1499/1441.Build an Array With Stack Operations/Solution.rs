impl Solution {
    pub fn build_array(target: Vec<i32>, n: i32) -> Vec<String> {
        let mut ans = Vec::new();
        let mut cur = 1;
        for &x in &target {
            while cur < x {
                ans.push("Push".to_string());
                ans.push("Pop".to_string());
                cur += 1;
            }
            ans.push("Push".to_string());
            cur += 1;
        }
        ans
    }
}
