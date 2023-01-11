impl Solution {
    pub fn number_of_pairs(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut count = [0; 101];
        for &v in nums.iter() {
            count[v as usize] += 1;
        }
        let mut sum = 0;
        for v in count.iter() {
            sum += v >> 1;
        }
        vec![sum as i32, (n - sum * 2) as i32]
    }
}
