impl Solution {
    pub fn build_array(target: Vec<i32>, n: i32) -> Vec<String> {
        let mut res = Vec::new();
        let mut cur = 1;
        for &num in target.iter() {
            while cur < num {
                res.push("Push");
                res.push("Pop");
                cur += 1;
            }
            res.push("Push");
            cur += 1;
        }
        res.into_iter().map(String::from).collect()
    }
}
