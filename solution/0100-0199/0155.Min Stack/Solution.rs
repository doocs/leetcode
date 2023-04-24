use std::collections::VecDeque;
struct MinStack {
    stk1: VecDeque<i32>,
    stk2: VecDeque<i32>,
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MinStack {

    fn new() -> Self {
        Self { stk1: VecDeque::new(), stk2: VecDeque::new() }
    }

    fn push(&mut self, x: i32) {
        self.stk1.push_back(x);
        if self.stk2.is_empty() || *self.stk2.back().unwrap() >= x {
            self.stk2.push_back(x);
        }
    }

    fn pop(&mut self) {
        let val = self.stk1.pop_back().unwrap();
        if *self.stk2.back().unwrap() == val {
            self.stk2.pop_back();
        }
    }

    fn top(&self) -> i32 {
        *self.stk1.back().unwrap()
    }

    fn get_min(&self) -> i32 {
        *self.stk2.back().unwrap()
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * let obj = MinStack::new();
 * obj.push(x);
 * obj.pop();
 * let ret_3: i32 = obj.top();
 * let ret_4: i32 = obj.get_min();
 */