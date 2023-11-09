use std::collections::HashMap;

impl Solution {
    pub fn min_number(nums1: Vec<i32>, nums2: Vec<i32>) -> i32 {
        let mut h1: HashMap<i32, bool> = HashMap::new();

        for &n in &nums1 {
            h1.insert(n, true);
        }

        let mut h2: HashMap<i32, bool> = HashMap::new();
        for &n in &nums2 {
            h2.insert(n, true);
        }

        let mut a = 0;
        let mut b = 0;
        for i in 1..10 {
            if h1.contains_key(&i) && h2.contains_key(&i) {
                return i;
            }

            if a == 0 && h1.contains_key(&i) {
                a = i;
            }

            if b == 0 && h2.contains_key(&i) {
                b = i;
            }
        }

        std::cmp::min(a * 10 + b, b * 10 + a)
    }
}
