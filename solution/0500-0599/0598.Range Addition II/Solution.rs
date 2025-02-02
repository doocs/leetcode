impl Solution {
    pub fn max_count(mut m: i32, mut n: i32, ops: Vec<Vec<i32>>) -> i32 {
        for op in ops {
            m = m.min(op[0]);
            n = n.min(op[1]);
        }
        m * n
    }
}
