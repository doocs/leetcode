impl Solution {
    pub fn last_visited_integers(nums: Vec<i32>) -> Vec<i32> {
        let mut seen: Vec<i32> = Vec::new();
        let mut ans: Vec<i32> = Vec::new();
        let mut k: i32 = 0;

        for x in nums {
            if x == -1 {
                k += 1;
                if k as usize > seen.len() {
                    ans.push(-1);
                } else {
                    ans.push(seen[seen.len() - k as usize]);
                }
            } else {
                k = 0;
                seen.push(x);
            }
        }

        ans
    }
}
