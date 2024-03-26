impl Solution {
    pub fn number_of_lines(widths: Vec<i32>, s: String) -> Vec<i32> {
        let mut lines = 1;
        let mut last = 0;

        for c in s.chars() {
            let idx = ((c as u8) - b'a') as usize;
            let w = widths[idx];
            if last + w <= 100 {
                last += w;
            } else {
                lines += 1;
                last = w;
            }
        }

        vec![lines, last]
    }
}
