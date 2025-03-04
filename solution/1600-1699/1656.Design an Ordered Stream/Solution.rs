struct OrderedStream {
    ptr: usize,
    data: Vec<Option<String>>,
}

impl OrderedStream {
    fn new(n: i32) -> Self {
        OrderedStream {
            ptr: 1,
            data: vec![None; (n + 1) as usize],
        }
    }

    fn insert(&mut self, id_key: i32, value: String) -> Vec<String> {
        self.data[id_key as usize] = Some(value);
        let mut ans = Vec::new();
        while self.ptr < self.data.len() && self.data[self.ptr].is_some() {
            ans.push(self.data[self.ptr].take().unwrap());
            self.ptr += 1;
        }
        ans
    }
}
