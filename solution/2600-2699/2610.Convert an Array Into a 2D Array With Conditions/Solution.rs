impl Solution {
    pub fn find_matrix(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let n = nums.len();
        let mut cnt = vec![0; n + 1];
        let mut ans: Vec<Vec<i32>> = Vec::new();

        for &x in &nums {
            cnt[x as usize] += 1;
        }

        for x in 1..=n as i32 {
            for j in 0..cnt[x as usize] {
                if ans.len() <= j {
                    ans.push(Vec::new());
                }
                ans[j].push(x);
            }
        }

        ans
    }
}
