use std::cmp::Ordering;

impl Solution {
    pub fn search_range(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let n = nums.len();
        let mut l = 0;
        let mut r = n;
        while l < r {
            let mid = l + (r - l) / 2;
            match nums[mid].cmp(&target) {
                Ordering::Less => l = mid + 1,
                Ordering::Greater => r = mid,
                Ordering::Equal => {
                    let mut res = vec![mid as i32, mid as i32];
                    let mut t = mid;
                    while l < t {
                        let mid = l + (t - l) / 2;
                        match nums[mid].cmp(&target) {
                            Ordering::Less => l = mid + 1,
                            Ordering::Greater => t = mid,
                            Ordering::Equal => {
                                res[0] = mid as i32;
                                t = mid;
                            }
                        }
                    }
                    t = mid + 1;
                    while t < r {
                        let mid = t + (r - t) / 2;
                        match nums[mid].cmp(&target) {
                            Ordering::Less => t = mid + 1,
                            Ordering::Greater => r = mid,
                            Ordering::Equal => {
                                res[1] = mid as i32;
                                t = mid + 1;
                            }
                        }
                    }
                    return res;
                }
            }
        }
        vec![-1, -1]
    }
}
