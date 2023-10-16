impl Solution {
    pub fn last_visited_integers(words: Vec<String>) -> Vec<i32> {
        let mut nums: Vec<i32> = Vec::new();
        let mut ans: Vec<i32> = Vec::new();
        let mut k = 0;

        for w in words {
            if w == "prev" {
                k += 1;
                let i = nums.len() as i32 - k;
                ans.push(if i < 0 { -1 } else { nums[i as usize] });
            } else {
                k = 0;
                nums.push(w.parse::<i32>().unwrap());
            }
        }

        ans
    }
}