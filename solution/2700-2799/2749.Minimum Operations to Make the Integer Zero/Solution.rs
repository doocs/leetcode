impl Solution {
    pub fn make_the_integer_zero(num1: i32, num2: i32) -> i32 {
        let num1 = num1 as i64;
        let num2 = num2 as i64;
        for k in 1.. {
            let x = num1 - k * num2;
            if x < 0 {
                break;
            }
            if (x.count_ones() as i64) <= k && k <= x {
                return k as i32;
            }
        }
        -1
    }
}
