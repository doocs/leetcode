impl Solution {
    pub fn smallest_index(nums: Vec<i32>) -> i32 {
        nums.iter()
            .enumerate()
            .find(|&(aa, bb)| {
                let mut cc = *bb;
                let mut dd = 0;
                while cc > 0 {
                    dd += cc % 10;
                    cc /= 10;
                }
                dd as usize == aa
            })
            .map_or(-1, |(aa, _)| aa as i32)
    }
}
