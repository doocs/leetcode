impl Solution {
    pub fn divide_array(mut nums: Vec<i32>, k: i32) -> Vec<Vec<i32>> {
        nums.sort();
        let mut ans = Vec::new();
        let n = nums.len();

        for i in (0..n).step_by(3) {
            if i + 2 >= n {
                return vec![];
            }

            let t = &nums[i..i + 3];
            if t[2] - t[0] > k {
                return vec![];
            }

            ans.push(t.to_vec());
        }

        ans
    }
}
