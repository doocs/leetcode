impl Solution {
    pub fn convert(s: String, num_rows: i32) -> String {
        let num_rows = num_rows as usize;
        if num_rows == 1 {
            return s;
        }
        let mut ss = vec![String::new(); num_rows];
        let mut i = 0;
        let mut to_down = true;
        for c in s.chars() {
            ss[i].push(c);
            if to_down {
                i += 1;
            } else {
                i -= 1;
            }
            if i == 0 || i == num_rows - 1 {
                to_down = !to_down;
            }
        }
        let mut res = String::new();
        for i in 0..num_rows {
            res += &ss[i];
        }
        res
    }
}
