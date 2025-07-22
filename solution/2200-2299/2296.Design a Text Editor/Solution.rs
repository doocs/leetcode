struct TextEditor {
    left: String,
    right: String,
}

impl TextEditor {
    fn new() -> Self {
        TextEditor {
            left: String::new(),
            right: String::new(),
        }
    }

    fn add_text(&mut self, text: String) {
        self.left.push_str(&text);
    }

    fn delete_text(&mut self, k: i32) -> i32 {
        let k = k.min(self.left.len() as i32) as usize;
        self.left.truncate(self.left.len() - k);
        k as i32
    }

    fn cursor_left(&mut self, k: i32) -> String {
        let k = k.min(self.left.len() as i32) as usize;
        for _ in 0..k {
            if let Some(c) = self.left.pop() {
                self.right.push(c);
            }
        }
        self.get_last_10_chars()
    }

    fn cursor_right(&mut self, k: i32) -> String {
        let k = k.min(self.right.len() as i32) as usize;
        for _ in 0..k {
            if let Some(c) = self.right.pop() {
                self.left.push(c);
            }
        }
        self.get_last_10_chars()
    }

    fn get_last_10_chars(&self) -> String {
        let len = self.left.len();
        self.left[len.saturating_sub(10)..].to_string()
    }
}
