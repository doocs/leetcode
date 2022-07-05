use std::collections::BTreeMap;

struct MyCalendar {
    bt: BTreeMap<i32, i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MyCalendar {
    fn new() -> Self {
        MyCalendar {
            bt: BTreeMap::new(),
        }
    }

    fn book(&mut self, start: i32, end: i32) -> bool {
        if let Some((_, &val)) = self.bt.range(..=start).last() {
            println!("{} {} {}", start, end, val);
            if val > start {
                return false;
            }
        }
        if let Some((&key, _)) = self.bt.range(start..).next() {
            if key < end {
                return false;
            }
        }
        self.bt.insert(start, end);
        true
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * let obj = MyCalendar::new();
 * let ret_1: bool = obj.book(start, end);
 */
