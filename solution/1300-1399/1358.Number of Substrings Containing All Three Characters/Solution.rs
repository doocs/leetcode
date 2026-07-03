impl Solution {
    pub fn number_of_substrings(s: String) -> i32 {
        let bytes = s.as_bytes();
        let mut d = [-1i32; 3];
        let mut ans: i32 = 0;

        for i in 0..bytes.len() {
            let c = (bytes[i] - b'a') as usize;
            d[c] = i as i32;

            let mn = d[0].min(d[1]).min(d[2]);
            ans += mn + 1;
        }

        ans
    }
}
