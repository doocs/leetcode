impl Solution {
    pub fn triangle_number(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let n = nums.len();
        let mut ans = 0;
        for i in 0..n.saturating_sub(2) {
            for j in i + 1..n.saturating_sub(1) {
                let sum = nums[i] + nums[j];
                let mut left = j + 1;
                let mut right = n;
                while left < right {
                    let mid = (left + right) / 2;
                    if nums[mid] < sum {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                if left > j + 1 {
                    ans += (left - 1 - j) as i32;
                }
            }
        }
        ans
    }
}
