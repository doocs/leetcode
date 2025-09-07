impl Solution {
    pub fn get_no_zero_integers(n: i32) -> Vec<i32> {
        for a in 1..n {
            let b = n - a;
            if !a.to_string().contains('0') && !b.to_string().contains('0') {
                return vec![a, b];
            }
        }
        vec![]
    }
}
