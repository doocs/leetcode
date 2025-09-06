impl Solution {
    pub fn product_queries(mut n: i32, queries: Vec<Vec<i32>>) -> Vec<i32> {
        let mut powers = Vec::new();
        while n > 0 {
            let x = n & -n;
            powers.push(x);
            n -= x;
        }
        let modulo = 1_000_000_007;
        let mut ans = Vec::with_capacity(queries.len());
        for q in queries {
            let l = q[0] as usize;
            let r = q[1] as usize;
            let mut x: i64 = 1;
            for j in l..=r {
                x = x * powers[j] as i64 % modulo;
            }
            ans.push(x as i32);
        }
        ans
    }
}
