struct MedianFinder {
    arr: Vec<i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MedianFinder {
    /** initialize your data structure here. */
    fn new() -> Self {
        MedianFinder {
            arr: vec![]
        }
    }

    fn add_num(&mut self, num: i32) {
        let mut l = 0;
        let mut r = self.arr.len();
        while l < r {
            let mid = l + r >> 1;
            if self.arr[mid] < num {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        self.arr.splice(l..l, [num]);
    }

    fn find_median(&self) -> f64 {
        let n = self.arr.len();
        match n % 2 == 0 {
            true => f64::from(self.arr[n >> 1] + self.arr[(n >> 1) - 1]) / 2.0,
            false => f64::from(self.arr[n >> 1]),
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * let obj = MedianFinder::new();
 * obj.add_num(num);
 * let ret_2: f64 = obj.find_median();
 */