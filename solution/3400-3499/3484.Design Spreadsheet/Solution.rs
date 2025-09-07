use std::collections::HashMap;

struct Spreadsheet {
    d: HashMap<String, i32>,
}

impl Spreadsheet {
    fn new(_rows: i32) -> Self {
        Spreadsheet { d: HashMap::new() }
    }

    fn set_cell(&mut self, cell: String, value: i32) {
        self.d.insert(cell, value);
    }

    fn reset_cell(&mut self, cell: String) {
        self.d.remove(&cell);
    }

    fn get_value(&self, formula: String) -> i32 {
        let mut ans = 0;
        for cell in formula[1..].split('+') {
            if cell.chars().next().unwrap().is_ascii_digit() {
                ans += cell.parse::<i32>().unwrap();
            } else {
                ans += *self.d.get(cell).unwrap_or(&0);
            }
        }
        ans
    }
}
