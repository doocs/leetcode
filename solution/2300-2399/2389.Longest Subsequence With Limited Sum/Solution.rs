impl Solution {
    pub fn answer_queries(mut nums: Vec<i32>, queries: Vec<i32>) -> Vec<i32> {
        nums.sort();

        for i in 1..nums.len() {
            nums[i] += nums[i - 1];
        }

        queries
            .iter()
            .map(|&q| match nums.binary_search(&q) {
                Ok(idx) => idx as i32 + 1,
                Err(idx) => idx as i32,
            })
            .collect()
    }
}
