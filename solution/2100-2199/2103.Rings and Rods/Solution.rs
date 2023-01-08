impl Solution {
    pub fn count_points(rings: String) -> i32 {
        let rings = rings.as_bytes();
        let target = (1 << b'R' - b'A') + (1 << b'G' - b'A') + (1 << b'B' - b'A');
        let n = rings.len();
        let mut count = [0; 10];
        let mut i = 0;
        while i < n {
            count[(rings[i + 1] - b'0') as usize] |= 1 << rings[i] - b'A';
            i += 2;
        }
        count.iter().filter(|&v| *v == target).count() as i32
    }
}
