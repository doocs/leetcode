impl Solution {
    pub fn total_hamming_distance(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        for i in 0..32 {
            let mut a = 0;
            for &x in nums.iter() {
                a += (x >> i) & 1;
            }
            let b = (nums.len() as i32) - a;
            ans += a * b;
        }
        ans
    }
}
