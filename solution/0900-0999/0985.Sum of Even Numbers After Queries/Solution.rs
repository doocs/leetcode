impl Solution {
    pub fn sum_even_after_queries(mut nums: Vec<i32>, queries: Vec<Vec<i32>>) -> Vec<i32> {
        let mut s: i32 = nums.iter().filter(|&x| x % 2 == 0).sum();
        let mut ans = Vec::with_capacity(queries.len());

        for query in queries {
            let (v, i) = (query[0], query[1] as usize);
            if nums[i] % 2 == 0 {
                s -= nums[i];
            }
            nums[i] += v;
            if nums[i] % 2 == 0 {
                s += nums[i];
            }
            ans.push(s);
        }

        ans
    }
}
