impl Solution {
    pub fn find_the_prefix_common_array(a: Vec<i32>, b: Vec<i32>) -> Vec<i32> {
        let mut ans = Vec::with_capacity(a.len());
        let (mut x, mut y): (u64, u64) = (0, 0);
        for (&a_val, &b_val) in a.iter().zip(b.iter()) {
            x |= 1 << a_val;
            y |= 1 << b_val;
            ans.push((x & y).count_ones() as i32);
        }
        ans
    }
}
