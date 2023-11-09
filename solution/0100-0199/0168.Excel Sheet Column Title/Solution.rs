impl Solution {
    #[allow(dead_code)]
    pub fn convert_to_title(column_number: i32) -> String {
        let mut ret = String::from("");
        let mut column_number = column_number;

        while column_number > 0 {
            if column_number <= 26 {
                ret.push((('A' as u8) + (column_number as u8) - 1) as char);
                break;
            } else {
                let mut left = column_number % 26;
                left = if left == 0 { 26 } else { left };
                ret.push((('A' as u8) + (left as u8) - 1) as char);
                column_number = (column_number - 1) / 26;
            }
        }

        ret.chars().rev().collect()
    }
}
