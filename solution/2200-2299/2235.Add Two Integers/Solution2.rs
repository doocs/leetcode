impl Solution {
    pub fn sum(num1: i32, num2: i32) -> i32 {
        let mut num1 = num1;
        let mut num2 = num2;
        while num2 != 0 {
            let carry = (num1 & num2) << 1;
            num1 ^= num2;
            num2 = carry;
        }
        num1
    }
}
