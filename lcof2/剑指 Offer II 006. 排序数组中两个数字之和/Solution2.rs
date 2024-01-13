use std::cmp::Ordering;

impl Solution {
    pub fn two_sum(numbers: Vec<i32>, target: i32) -> Vec<i32> {
        let n = numbers.len();
        for i in 0..n - 1 {
            let num = target - numbers[i];
            let mut l = i + 1;
            let mut r = n - 1;
            while l <= r {
                let mid = l + (r - l) / 2;
                match num.cmp(&numbers[mid]) {
                    Ordering::Less => {
                        r = mid - 1;
                    }
                    Ordering::Greater => {
                        l = mid + 1;
                    }
                    Ordering::Equal => {
                        return vec![i as i32, mid as i32];
                    }
                }
            }
        }
        vec![-1, -1]
    }
}
