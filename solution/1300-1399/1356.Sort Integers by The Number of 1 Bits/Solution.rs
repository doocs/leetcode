impl Solution {
    pub fn sort_by_bits(mut arr: Vec<i32>) -> Vec<i32> {
        arr.sort_by(|a, b| {
            let res = a.count_ones().cmp(&b.count_ones());
            if res == std::cmp::Ordering::Equal {
                return a.cmp(&b);
            }
            res
        });
        arr
    }
}
