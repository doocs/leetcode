impl Solution {
    pub fn find_longest_chain(mut pairs: Vec<Vec<i32>>) -> i32 {
        pairs.sort_by_key(|pair| pair[1]);
        let mut ans = 0;
        let mut pre = i32::MIN;
        for pair in pairs {
            let (a, b) = (pair[0], pair[1]);
            if pre < a {
                ans += 1;
                pre = b;
            }
        }
        ans
    }
}
