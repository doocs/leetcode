impl Solution {
    pub fn smallest_number(n: i32) -> i32 {
        let mut x = 1;
        while x - 1 < n {
            x <<= 1;
        }
        x - 1
    }
}
