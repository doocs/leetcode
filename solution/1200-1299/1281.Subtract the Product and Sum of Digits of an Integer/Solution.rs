impl Solution {
    pub fn subtract_product_and_sum(mut n: i32) -> i32 {
        let mut p = 1;
        let mut s = 0;
        while n != 0 {
            let num = n % 10;
            n /= 10;
            p *= num;
            s += num;
        }
        p - s
    }
}
