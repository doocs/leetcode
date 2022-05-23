use std::collections::VecDeque;
struct SortedStack {
    stack: VecDeque<i32>
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl SortedStack {

    fn new() -> Self {
        Self { stack: VecDeque::new() }
    }

    fn push(&mut self, val: i32) {
        if self.is_empty() || self.peek() > val {
            self.stack.push_back(val);
            return;
        }
        let t = self.stack.pop_back().unwrap();
        self.push(val);
        self.stack.push_back(t);
    }

    fn pop(&mut self) {
        self.stack.pop_back();
    }

    fn peek(&self) -> i32 {
        *self.stack.back().unwrap_or(&-1)
    }

    fn is_empty(&self) -> bool {
        self.stack.is_empty()
    }
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * let obj = SortedStack::new();
 * obj.push(val);
 * obj.pop();
 * let ret_3: i32 = obj.peek();
 * let ret_4: bool = obj.is_empty();
 */