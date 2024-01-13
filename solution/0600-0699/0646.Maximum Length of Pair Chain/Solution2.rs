impl Solution {
    pub fn find_longest_chain(mut pairs: Vec<Vec<i32>>) -> i32 {
        pairs.sort_by(|a, b| a[1].cmp(&b[1]));
        let mut res = 0;
        let mut pre = i32::MIN;
        for pair in pairs.iter() {
            let a = pair[0];
            let b = pair[1];
            if pre < a {
                pre = b;
                res += 1;
            }
        }
        res
    }
}
