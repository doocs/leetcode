use std::collections::VecDeque;
struct StockSpanner {
    stk: VecDeque<(i32, i32)>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl StockSpanner {
    fn new() -> Self {
        Self {
            stk: vec![(i32::MAX, -1)].into_iter().collect(),
        }
    }

    fn next(&mut self, price: i32) -> i32 {
        let mut cnt = 1;
        while self.stk.back().unwrap().0 <= price {
            cnt += self.stk.pop_back().unwrap().1;
        }
        self.stk.push_back((price, cnt));
        cnt
    }
}
