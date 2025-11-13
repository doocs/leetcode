impl Solution {
    pub fn xor_after_queries(mut nums: Vec<i32>, queries: Vec<Vec<i32>>) -> i32 {
        let modv: i64 = 1_000_000_007;
        for q in queries {
            let (l, r, k, v) = (q[0] as usize, q[1] as usize, q[2] as usize, q[3] as i64);
            let mut idx = l;
            while idx <= r {
                nums[idx] = ((nums[idx] as i64 * v) % modv) as i32;
                idx += k;
            }
        }
        let mut ans = 0;
        for x in nums {
            ans ^= x;
        }
        return ans;
    }
}
