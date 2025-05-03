impl Solution {
    pub fn num_equiv_domino_pairs(dominoes: Vec<Vec<i32>>) -> i32 {
        let mut cnt = [0i32; 100];
        let mut ans = 0;

        for d in dominoes {
            let a = d[0] as usize;
            let b = d[1] as usize;
            let key = if a < b { a * 10 + b } else { b * 10 + a };
            ans += cnt[key];
            cnt[key] += 1;
        }

        ans
    }
}
