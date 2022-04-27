impl Solution {
    pub fn chalk_replacer(chalk: Vec<i32>, k: i32) -> i32 {
        let pre_sum: Vec<i64> = chalk
            .into_iter()
            .map(|x| x as i64)
            .scan(0, |state, x| {
                *state += x;
                Some(*state)
            })
            .collect();

        pre_sum
            .binary_search(&(k as i64 % pre_sum.last().unwrap()))
            .map_or_else(|e| e, |v| v + 1) as i32
    }
}
