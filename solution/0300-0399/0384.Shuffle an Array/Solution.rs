use rand::Rng;
struct Solution {
    nums: Vec<i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl Solution {
    fn new(nums: Vec<i32>) -> Self {
        Self { nums }
    }

    fn reset(&self) -> Vec<i32> {
        self.nums.clone()
    }

    fn shuffle(&mut self) -> Vec<i32> {
        let n = self.nums.len();
        let mut res = self.nums.clone();
        for i in 0..n {
            let j = rand::thread_rng().gen_range(0, n);
            res.swap(i, j);
        }
        res
    }
}
