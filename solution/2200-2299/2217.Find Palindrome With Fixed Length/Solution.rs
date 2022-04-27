impl Solution {
    pub fn kth_palindrome(queries: Vec<i32>, int_length: i32) -> Vec<i64> {
        let is_odd = int_length & 1 == 1;
        let best_num = i32::pow(10, (int_length / 2 + if is_odd { 0 } else { -1 }) as u32);
        let max = best_num * 9;
        queries
            .iter()
            .map(|&num| {
                if num > max {
                    return -1;
                }
                let num = best_num + num - 1;
                format!(
                    "{}{}",
                    num,
                    num.to_string()
                        .chars()
                        .rev()
                        .skip(if is_odd { 1 } else { 0 })
                        .collect::<String>()
                )
                .parse()
                .unwrap()
            })
            .collect()
    }
}
