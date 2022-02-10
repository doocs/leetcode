impl Solution {
    fn gcd(a: i32, b: i32) -> i32 {
        match b {
            0 => a,
            _ => Solution::gcd(b, a % b),
        }
    }

    pub fn simplified_fractions(n: i32) -> Vec<String> {
        let mut res = vec![];
        for i in 1..n {
            for j in i + 1..=n {
                if Solution::gcd(i, j) == 1 {
                    res.push(format!("{}/{}", i, j));
                }
            }
        }
        res
    }
}
