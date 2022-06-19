use std::cmp::Ordering;
impl Solution {
    pub fn check_if_exist(mut arr: Vec<i32>) -> bool {
        arr.sort();
        let n = arr.len();
        for i in 0..n {
            let target = arr[i] * 2;
            let mut left = 0;
            let mut right = n;
            while left < right {
                let mid = left + (right - left) / 2;
                match arr[mid].cmp(&target) {
                    Ordering::Less => left = mid + 1,
                    Ordering::Greater => right = mid,
                    Ordering::Equal => {
                        if mid == i {
                            break;
                        }
                        return true;
                    }
                }
            }
        }
        false
    }
}