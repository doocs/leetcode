struct OrderedStream {
    ptr: usize,
    vals: Vec<Option<String>>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl OrderedStream {
    fn new(n: i32) -> Self {
        Self { ptr: 0, vals: vec![None; n as usize] }
    }

    fn insert(&mut self, id_key: i32, value: String) -> Vec<String> {
        self.vals[(id_key - 1) as usize] = Some(value);
        let mut res = Vec::new();
        while self.ptr < self.vals.len() {
            if let Some(s) = &self.vals[self.ptr] {
                res.push(s.clone());
                self.ptr += 1;
            } else {
                break;
            }
        }
        res
    }
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * let obj = OrderedStream::new(n);
 * let ret_1: Vec<String> = obj.insert(idKey, value);
 */