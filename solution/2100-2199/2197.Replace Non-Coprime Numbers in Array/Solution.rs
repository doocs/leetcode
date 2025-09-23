impl Solution {
    pub fn replace_non_coprimes(nums: Vec<i32>) -> Vec<i32> {
        fn gcd(mut a: i64, mut b: i64) -> i64 {
            while b != 0 {
                let t = a % b;
                a = b;
                b = t;
            }
            a
        }

        let mut stk: Vec<i64> = Vec::new();
        for x in nums {
            stk.push(x as i64);
            while stk.len() > 1 {
                let x = *stk.last().unwrap();
                let y = stk[stk.len() - 2];
                let g = gcd(x, y);
                if g == 1 {
                    break;
                }
                stk.pop();
                let last = stk.last_mut().unwrap();
                *last = x / g * y;
            }
        }

        stk.into_iter().map(|v| v as i32).collect()
    }
}
