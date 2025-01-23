impl Solution {
    pub fn maximum_even_split(mut final_sum: i64) -> Vec<i64> {
        let mut ans = Vec::new();
        if final_sum % 2 != 0 {
            return ans;
        }
        let mut i = 2;
        while i <= final_sum {
            ans.push(i);
            final_sum -= i;
            i += 2;
        }
        if let Some(last) = ans.last_mut() {
            *last += final_sum;
        }
        ans
    }
}
