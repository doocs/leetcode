struct SmallestInfiniteSet {
    counter: [bool; 1000]
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl SmallestInfiniteSet {

    fn new() -> Self {
        Self {
            counter: [true; 1000]
        }
    }

    fn pop_smallest(&mut self) -> i32 {
        for i in 0..1000 {
            if self.counter[i] {
                self.counter[i] = false;
                return i as i32 + 1;
            }
        }
        -1
    }

    fn add_back(&mut self, num: i32) {
        self.counter[num as usize - 1] = true;
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * let obj = SmallestInfiniteSet::new();
 * let ret_1: i32 = obj.pop_smallest();
 * obj.add_back(num);
 */