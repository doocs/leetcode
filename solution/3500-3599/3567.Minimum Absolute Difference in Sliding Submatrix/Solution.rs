impl Solution {
    pub fn min_abs_diff(grid: Vec<Vec<i32>>, k: i32) -> Vec<Vec<i32>> {
        let m = grid.len();
        let n = grid[0].len();
        let k = k as usize;

        let mut ans = vec![vec![0; n - k + 1]; m - k + 1];

        for i in 0..=m - k {
            for j in 0..=n - k {
                let mut nums = Vec::with_capacity(k * k);
                for x in i..i + k {
                    for y in j..j + k {
                        nums.push(grid[x][y]);
                    }
                }

                nums.sort_unstable();

                let mut d = i32::MAX;
                for t in 1..nums.len() {
                    if nums[t] != nums[t - 1] {
                        d = d.min((nums[t] - nums[t - 1]).abs());
                    }
                }

                ans[i][j] = if d == i32::MAX { 0 } else { d };
            }
        }

        ans
    }
}
