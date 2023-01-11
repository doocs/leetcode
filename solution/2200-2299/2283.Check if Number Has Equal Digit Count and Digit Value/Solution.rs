impl Solution {
    pub fn digit_count(num: String) -> bool {
        let s = num.as_bytes();
        let n = num.len();
        let mut count = [0; 10];
        for i in 0..n {
            count[i] = s[i] - b'0';
        }
        for c in s {
            count[(c - b'0') as usize] -= 1;
        }
        count.iter().all(|v| *v == 0)
    }
}
