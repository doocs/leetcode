impl Solution {
    pub fn get_smallest_string(s: String) -> String {
        let mut cs: Vec<u8> = s.into_bytes();
        let n = cs.len();
        for i in 1..n {
            let (a, b) = (cs[i - 1], cs[i]);
            if a > b && a % 2 == b % 2 {
                cs.swap(i - 1, i);
                return String::from_utf8(cs).unwrap();
            }
        }
        String::from_utf8(cs).unwrap()
    }
}
