impl Solution {
    pub fn subtract_product_and_sum(mut n: i32) -> i32 {
        let mut x = 1;
        let mut y = 0;
        while n != 0 {
            let v = n % 10;
            n /= 10;
            x *= v;
            y += v;
        }
        x - y
    }
}
