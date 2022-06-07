use std::collections::VecDeque;
struct MaxQueue {
    queue: VecDeque<i32>,
    deque: VecDeque<i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MaxQueue {
    fn new() -> Self {
        Self {
            queue: VecDeque::new(),
            deque: VecDeque::new(),
        }
    }

    fn max_value(&self) -> i32 {
        *self.deque.front().unwrap_or(&-1)
    }

    fn push_back(&mut self, value: i32) {
        self.queue.push_back(value);
        while !self.deque.is_empty() && *self.deque.back().unwrap() < value {
            self.deque.pop_back();
        }
        self.deque.push_back(value);
    }

    fn pop_front(&mut self) -> i32 {
        if self.queue.is_empty() {
            return -1;
        }
        let res = self.queue.pop_front().unwrap();
        if res == self.deque[0] {
            self.deque.pop_front();
        }
        res
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * let obj = MaxQueue::new();
 * let ret_1: i32 = obj.max_value();
 * obj.push_back(value);
 * let ret_3: i32 = obj.pop_front();
 */