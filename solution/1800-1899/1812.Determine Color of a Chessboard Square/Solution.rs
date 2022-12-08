impl Solution {
    pub fn square_is_white(coordinates: String) -> bool {
        let s = coordinates.as_bytes();
        s[0] + s[1] & 1 == 1
    }
}
