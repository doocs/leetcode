impl Solution {
    pub fn min_partitions(n: String) -> i32 {
        n.as_bytes().iter().fold(0, |ans, &c| ans.max((c - b'0') as i32))
    }
}
