impl Solution {
    pub fn check(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let cnt = nums.iter().enumerate().fold(0, |cnt, (i, &x)| {
            cnt + if x > nums[(i + 1) % n] { 1 } else { 0 }
        });
        cnt <= 1
    }
}
