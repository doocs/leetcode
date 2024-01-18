impl Solution {
    pub fn shuffle(mut nums: Vec<i32>, n: i32) -> Vec<i32> {
        let n = n as usize;
        for i in 0..n * 2 {
            let mut j = i;
            while nums[i] > 0 {
                j = if j < n { 2 * j } else { 2 * (j - n) + 1 };
                nums.swap(i, j);
                nums[j] *= -1;
            }
        }
        for i in 0..n * 2 {
            nums[i] *= -1;
        }
        nums
    }
}
