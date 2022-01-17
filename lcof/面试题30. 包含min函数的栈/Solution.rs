struct MinStack {
    items: Vec<i32>,
    min: Vec<i32>,
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MinStack {

    /** initialize your data structure here. */
    fn new() -> Self {
        MinStack {
            items: Vec::new(),
            min: Vec::new(),
        }
    }
    
    fn push(&mut self, x: i32) {
        self.items.push(x);
        match self.min.last() {
            Some(min) => {
                if *min >= x {
                    self.min.push(x)
                }
            },
            None => self.min.push(x)
        }
    }
    
    fn pop(&mut self) {
        if self.items.pop().unwrap() == *self.min.last().unwrap() {
            self.min.pop();
        }
    }
    
    fn top(&self) -> i32 {
        *self.items.last().unwrap()
    }
    
    fn min(&self) -> i32 {
        *self.min.last().unwrap()
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * let obj = MinStack::new();
 * obj.push(x);
 * obj.pop();
 * let ret_3: i32 = obj.top();
 * let ret_4: i32 = obj.min();
 */