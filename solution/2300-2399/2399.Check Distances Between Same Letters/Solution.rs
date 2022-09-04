impl Solution {
    pub fn check_distances(s: String, distance: Vec<i32>) -> bool {
        let n = s.len();
        let s = s.as_bytes();
        let mut d = [0; 26];
        for i in 0..n {
            let j = (s[i] - b'a') as usize;
            let i = i as i32;
            if d[j] > 0 && i - d[j] != distance[j] {
                return false;
            }
            d[j] = i + 1;
        }
        true
    }
}
