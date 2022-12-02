impl Solution {
    pub fn final_value_after_operations(operations: Vec<String>) -> i32 {
        let mut ans = 0;
        for s in operations.iter() {
            ans += if s.as_bytes()[1] == b'+' { 1 } else { -1 };
        }
        ans
    }
}
