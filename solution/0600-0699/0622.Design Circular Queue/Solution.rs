struct MyCircularQueue {
    queue: Vec<i32>,
    left: usize,
    right: usize,
    capacity: usize,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MyCircularQueue {
    fn new(k: i32) -> Self {
        let k = k as usize;
        Self {
            queue: vec![0; k],
            left: 0,
            right: 0,
            capacity: k,
        }
    }

    fn en_queue(&mut self, value: i32) -> bool {
        if self.is_full() {
            return false;
        }
        self.queue[self.right % self.capacity] = value;
        self.right += 1;
        true
    }

    fn de_queue(&mut self) -> bool {
        if self.is_empty() {
            return false;
        }
        self.left += 1;
        true
    }

    fn front(&self) -> i32 {
        if self.is_empty() {
            return -1;
        }
        self.queue[self.left % self.capacity]
    }

    fn rear(&self) -> i32 {
        if self.is_empty() {
            return -1;
        }
        self.queue[(self.right - 1) % self.capacity]
    }

    fn is_empty(&self) -> bool {
        self.right - self.left == 0
    }

    fn is_full(&self) -> bool {
        self.right - self.left == self.capacity
    }
}
