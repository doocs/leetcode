impl Solution {
    fn max_length(ribbons: Vec<i32>, k: i32) -> i32 {
        let mut left = 0i32;
        let mut right = *ribbons.iter().max().unwrap();
        while left < right {
            let mid = (left + right + 1) / 2;
            let mut cnt = 0i32;
            for &entry in ribbons.iter() {
                cnt += entry / mid;
                if cnt >= k {
                    break;
                }
            }
            if cnt >= k {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
