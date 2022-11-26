struct NumArray {
    sums: Vec<i32>,
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl NumArray {
    fn new(mut nums: Vec<i32>) -> Self {
        let n = nums.len();
        let mut sums = vec![0; n + 1];
        for i in 0..n {
            sums[i + 1] = sums[i] + nums[i];
        }
        Self { sums }
    }

    fn sum_range(&self, left: i32, right: i32) -> i32 {
        self.sums[(right + 1) as usize] - self.sums[left as usize]
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * let obj = NumArray::new(nums);
 * let ret_1: i32 = obj.sum_range(left, right);
 */
