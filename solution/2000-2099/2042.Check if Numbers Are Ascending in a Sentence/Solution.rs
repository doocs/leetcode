impl Solution {
    pub fn are_numbers_ascending(s: String) -> bool {
        let mut pre = -1;
        for cur in s.split(' ') {
            if cur.as_bytes()[0] <= b'9' {
                let num = cur.parse::<i32>().unwrap();
                if num <= pre {
                    return false;
                }
                pre = num;
            }
        }
        true
    }
}
