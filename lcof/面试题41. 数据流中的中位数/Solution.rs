struct MedianFinder {
    nums: Vec<i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MedianFinder {
    /** initialize your data structure here. */
    fn new() -> Self {
        Self { nums: Vec::new() }
    }

    fn add_num(&mut self, num: i32) {
        let mut l = 0;
        let mut r = self.nums.len();
        while l < r {
            let mid = l + r >> 1;
            if self.nums[mid] < num {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        self.nums.insert(l, num);
    }

    fn find_median(&self) -> f64 {
        let n = self.nums.len();
        if (n & 1) == 1 {
            return f64::from(self.nums[n >> 1]);
        }
        f64::from(self.nums[n >> 1] + self.nums[(n >> 1) - 1]) / 2.0
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * let obj = MedianFinder::new();
 * obj.add_num(num);
 * let ret_2: f64 = obj.find_median();
 */