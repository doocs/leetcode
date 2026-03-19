impl Solution {
    pub fn read_binary_watch(turned_on: i32) -> Vec<String> {
        let mut ans: Vec<String> = Vec::new();

        for i in 0u32..(1 << 10) {
            let h = i >> 6;
            let m = i & 0b111111;

            if h < 12 && m < 60 && i.count_ones() as i32 == turned_on {
                ans.push(format!("{}:{:02}", h, m));
            }
        }

        ans
    }
}
