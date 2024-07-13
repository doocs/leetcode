use std::collections::VecDeque;

struct SortedStack {
    stk: VecDeque<i32>,
}

impl SortedStack {
    fn new() -> Self {
        SortedStack {
            stk: VecDeque::new(),
        }
    }

    fn push(&mut self, val: i32) {
        let mut t = VecDeque::new();
        while let Some(top) = self.stk.pop_back() {
            if top < val {
                t.push_back(top);
            } else {
                self.stk.push_back(top);
                break;
            }
        }
        self.stk.push_back(val);
        while let Some(top) = t.pop_back() {
            self.stk.push_back(top);
        }
    }

    fn pop(&mut self) {
        if !self.is_empty() {
            self.stk.pop_back();
        }
    }

    fn peek(&self) -> i32 {
        if self.is_empty() {
            -1
        } else {
            *self.stk.back().unwrap()
        }
    }

    fn is_empty(&self) -> bool {
        self.stk.is_empty()
    }
}
