impl Solution {
    pub fn minimum_boxes(apple: Vec<i32>, mut capacity: Vec<i32>) -> i32 {
        capacity.sort();

        let mut s: i32 = apple.iter().sum();

        let n = capacity.len();
        let mut i = 1;
        loop {
            s -= capacity[n - i];
            if s <= 0 {
                return i as i32;
            }
            i += 1;
        }
    }
}
