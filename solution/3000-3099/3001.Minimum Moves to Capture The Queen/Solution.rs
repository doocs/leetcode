impl Solution {
    pub fn min_moves_to_capture_the_queen(a: i32, b: i32, c: i32, d: i32, e: i32, f: i32) -> i32 {
        if a == e && (c != a || (d - b) * (d - f) > 0) {
            return 1;
        }
        if b == f && (d != b || (c - a) * (c - e) > 0) {
            return 1;
        }
        if c - e == d - f && (a - e != b - f || (a - c) * (a - e) > 0) {
            return 1;
        }
        if c - e == f - d && (a - e != f - b || (a - c) * (a - e) > 0) {
            return 1;
        }
        return 2;
    }
}
