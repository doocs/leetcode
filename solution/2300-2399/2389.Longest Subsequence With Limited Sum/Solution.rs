impl Solution {
    pub fn answer_queries(mut nums: Vec<i32>, queries: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        nums.sort();
        queries
            .into_iter()
            .map(|query| {
                let mut sum = 0;
                for i in 0..n {
                    sum += nums[i];
                    if sum > query {
                        return i as i32;
                    }
                }
                n as i32
            })
            .collect()
    }
}