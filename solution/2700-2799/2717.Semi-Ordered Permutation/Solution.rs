impl Solution {
    pub fn semi_ordered_permutation(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let i = nums
            .iter()
            .enumerate()
            .find(|&(_, &v)| v == 1)
            .map(|(i, _)| i)
            .unwrap();
        let j = nums
            .iter()
            .enumerate()
            .find(|&(_, &v)| v == (n as i32))
            .map(|(i, _)| i)
            .unwrap();

        let mut ans = i - 1 + n - j;
        if i > j {
            ans = i - 1 + n - j - 1;
        }

        ans as i32
    }
}
