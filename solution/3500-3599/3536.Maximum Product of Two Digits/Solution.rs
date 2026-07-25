impl Solution {
    pub fn max_product(mut n: i32) -> i32 {
        let (mut a, mut b) = (0, 0);

        while n > 0 {
            let x = n % 10;
            if a < x {
                b = a;
                a = x;
            } else if b < x {
                b = x;
            }
            n /= 10;
        }

        a * b
    }
}
