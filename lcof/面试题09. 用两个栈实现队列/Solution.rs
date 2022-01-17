struct CQueue {
    s1: Vec<i32>,
    s2: Vec<i32>
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl CQueue {

    fn new() -> Self {
        CQueue {
            s1: Vec::new(),
            s2: Vec::new(),
        }
    }
    
    fn append_tail(&mut self, value: i32) {
        self.s1.push(value);
    }
    
    fn delete_head(&mut self) -> i32 {
        match self.s2.pop() {
            Some(value) => value,
            None => {
                while !self.s1.is_empty() {
                    self.s2.push(self.s1.pop().unwrap());
                }
                self.s2.pop().unwrap_or(-1)
            }
        }
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * let obj = CQueue::new();
 * obj.append_tail(value);
 * let ret_2: i32 = obj.delete_head();
 */