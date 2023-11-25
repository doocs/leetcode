impl Solution {
    pub fn maximum_sum(nums: Vec<i32>) -> i32 {
        let mut d = vec![0; 100];
        let mut ans = -1;

        for &v in nums.iter() {
            let mut x: usize = 0;
            let mut y = v;
            while y > 0 {
                x += (y % 10) as usize;
                y /= 10;
            }
            if d[x] > 0 {
                ans = ans.max(d[x] + v);
            }
            d[x] = d[x].max(v);
        }

        ans
    }
}
