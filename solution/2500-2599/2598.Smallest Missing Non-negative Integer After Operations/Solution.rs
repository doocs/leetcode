impl Solution {
    pub fn find_smallest_integer(nums: Vec<i32>, value: i32) -> i32 {
        let mut cnt = vec![0; value as usize];
        for &x in &nums {
            let idx = ((x % value + value) % value) as usize;
            cnt[idx] += 1;
        }

        let mut i = 0;
        loop {
            let idx = (i % value) as usize;
            if cnt[idx] == 0 {
                return i;
            }
            cnt[idx] -= 1;
            i += 1;
        }
    }
}
