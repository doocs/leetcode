impl Solution {
    pub fn read_binary_watch(turned_on: i32) -> Vec<String> {
        let mut ans: Vec<String> = Vec::new();

        for i in 0u32..12 {
            for j in 0u32..60 {
                if (i.count_ones() + j.count_ones()) as i32 == turned_on {
                    ans.push(format!("{}:{:02}", i, j));
                }
            }
        }

        ans
    }
}
