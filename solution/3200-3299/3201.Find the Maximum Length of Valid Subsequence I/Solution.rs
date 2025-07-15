impl Solution {
    pub fn maximum_length(nums: Vec<i32>) -> i32 {
        let mut f = [[0; 2]; 2];
        let mut ans = 0;
        for x in nums {
            let x = (x % 2) as usize;
            for j in 0..2 {
                let y = ((j + 2 - x) % 2) as usize;
                f[x][y] = f[y][x] + 1;
                ans = ans.max(f[x][y]);
            }
        }
        ans
    }
}
