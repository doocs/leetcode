impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        let mut counts = [0; 32];
        for num in nums.iter() {
            for i in 0..32 {
                counts[i] += (num >> i) & 1;
            }
        }
        let mut res = 0;
        for count in counts.iter().rev() {
            res <<= 1;
            res |= count % 3;
        }
        res
    }
}
