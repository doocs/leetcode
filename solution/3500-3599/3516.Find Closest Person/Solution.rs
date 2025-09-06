impl Solution {
    pub fn find_closest(x: i32, y: i32, z: i32) -> i32 {
        let a = (x - z).abs();
        let b = (y - z).abs();
        if a == b {
            0
        } else if a < b {
            1
        } else {
            2
        }
    }
}
