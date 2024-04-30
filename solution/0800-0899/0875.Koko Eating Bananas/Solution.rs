impl Solution {
    pub fn min_eating_speed(piles: Vec<i32>, h: i32) -> i32 {
        let mut l = 1;
        let mut r = *piles.iter().max().unwrap_or(&0);
        while l < r {
            let mid = (l + r) >> 1;
            let mut s = 0;
            for x in piles.iter() {
                s += (x + mid - 1) / mid;
            }
            if s <= h {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        l
    }
}
