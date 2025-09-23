impl Solution {
    pub fn get_no_zero_integers(n: i32) -> Vec<i32> {
        fn f(mut x: i32) -> bool {
            while x > 0 {
                if x % 10 == 0 {
                    return false;
                }
                x /= 10;
            }
            true
        }

        for a in 1..n {
            let b = n - a;
            if f(a) && f(b) {
                return vec![a, b];
            }
        }
        vec![]
    }
}
