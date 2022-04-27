impl Solution {
    pub fn subtract_product_and_sum(mut n: i32) -> i32 {
        let mut mul = 1;
        let mut sum = 0;
        while n != 0 {
            let num = n % 10;
            n /= 10;
            mul *= num;
            sum += num;
        }
        mul - sum
    }
}
