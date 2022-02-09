impl Solution {
    pub fn my_sqrt(x: i32) -> i32 {
        if x < 2 {
            return x;
        }
        let mut l = 1;
        let mut r = x / 2;
        while l < r {
            let mid = (l + r + 1) >> 1;
            if x / mid < mid {
                r = mid - 1
            } else {
                l = mid;
            }
        }
        l
    }
}
