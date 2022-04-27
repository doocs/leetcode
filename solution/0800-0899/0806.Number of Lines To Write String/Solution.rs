impl Solution {
    pub fn number_of_lines(widths: Vec<i32>, s: String) -> Vec<i32> {
        let mut count = 1;
        let mut sum = 0;
        for c in s.as_bytes() {
            let width = widths[(c - b'a') as usize];
            if sum + width > 100 {
                sum = 0;
                count += 1;
            }
            sum += width;
        }
        vec![count, sum]
    }
}
