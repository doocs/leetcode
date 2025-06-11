impl Solution {
    pub fn convert(s: String, num_rows: i32) -> String {
        if num_rows == 1 {
            return s;
        }

        let num_rows = num_rows as usize;
        let mut g = vec![String::new(); num_rows];
        let mut i = 0;
        let mut k = -1;

        for c in s.chars() {
            g[i].push(c);
            if i == 0 || i == num_rows - 1 {
                k = -k;
            }
            i = (i as isize + k) as usize;
        }

        g.concat()
    }
}
