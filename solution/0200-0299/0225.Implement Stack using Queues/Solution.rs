use std::collections::VecDeque;

struct MyStack {
    /// There could only be two status at all time
    /// 1. One contains N elements, the other is empty
    /// 2. One contains N - 1 elements, the other contains exactly 1 element
    q_1: VecDeque<i32>,
    q_2: VecDeque<i32>,
    // Either 1 or 2, originally begins from 1
    index: i32,
}

impl MyStack {
    fn new() -> Self {
        Self {
            q_1: VecDeque::new(),
            q_2: VecDeque::new(),
            index: 1,
        }
    }

    fn move_data(&mut self) {
        // Always move from q1 to q2
        assert!(self.q_2.len() == 1);
        while !self.q_1.is_empty() {
            self.q_2.push_back(self.q_1.pop_front().unwrap());
        }
        let tmp = self.q_1.clone();
        self.q_1 = self.q_2.clone();
        self.q_2 = tmp;
    }

    fn push(&mut self, x: i32) {
        self.q_2.push_back(x);
        self.move_data();
    }

    fn pop(&mut self) -> i32 {
        self.q_1.pop_front().unwrap()
    }

    fn top(&mut self) -> i32 {
        *self.q_1.front().unwrap()
    }

    fn empty(&self) -> bool {
        self.q_1.is_empty()
    }
}
