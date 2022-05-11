impl Solution {
    pub fn min_deletion_size(strs: Vec<String>) -> i32 {
        let n = strs.len();
        let m = strs[0].len();
        let mut res = 0;
        for i in 0..m {
            for j in 1..n {
                if strs[j - 1].as_bytes()[i] > strs[j].as_bytes()[i] {
                    res += 1;
                    break;
                }
            }
        }
        res
    }
}
