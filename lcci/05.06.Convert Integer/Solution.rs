impl Solution {
    pub fn convert_integer(a: i32, b: i32) -> i32 {
        (a ^ b).count_ones() as i32
    }
}
