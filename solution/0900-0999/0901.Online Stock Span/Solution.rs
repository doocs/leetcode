use std::collections::VecDeque;
struct StockSpanner {
    stack: VecDeque<(i32, i32)>,
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl StockSpanner {
    fn new() -> Self {
        Self {
            stack: vec![(i32::MAX, -1)].into_iter().collect()
        }
    }

    fn next(&mut self, price: i32) -> i32 {
        let mut res = 1;
        while self.stack.back().unwrap().0 <= price {
            res += self.stack.pop_back().unwrap().1;
        }
        self.stack.push_back((price, res));
        res
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * let obj = StockSpanner::new();
 * let ret_1: i32 = obj.next(price);
 */