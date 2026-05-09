impl Solution {
    pub fn longest_common_prefix(arr1: Vec<i32>, arr2: Vec<i32>) -> i32 {
        let mut s = std::collections::HashSet::new();
        for x in arr1 {
            let mut y = x;
            while y > 0 {
                s.insert(y);
                y /= 10;
            }
        }
        let mut mx = 0;
        for x in arr2 {
            let mut y = x;
            while y > 0 {
                if s.contains(&y) {
                    mx = mx.max(y);
                    break;
                }
                y /= 10;
            }
        }
        if mx > 0 {
            (mx as f64).log10().floor() as i32 + 1
        } else {
            0
        }
    }
}
