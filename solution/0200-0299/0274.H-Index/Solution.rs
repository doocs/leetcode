impl Solution {
    #[allow(dead_code)]
    pub fn h_index(citations: Vec<i32>) -> i32 {
        let mut citations = citations;
        citations.sort_by(|&lhs, &rhs| { rhs.cmp(&lhs) });

        let n = citations.len();

        for i in (1..=n).rev() {
            if citations[i - 1] >= (i as i32) {
                return i as i32;
            }
        }

        0
    }
}
