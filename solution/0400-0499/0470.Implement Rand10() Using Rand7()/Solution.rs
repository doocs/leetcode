/**
 * The rand7() API is already defined for you.
 * @return a random integer in the range 1 to 7
 * fn rand7() -> i32;
 */

impl Solution {
    pub fn rand10() -> i32 {
        loop {
            let i = rand7() - 1;
            let j = rand7();
            let x = i * 7 + j;
            if x <= 40 {
                return (x % 10) + 1;
            }
        }
    }
}
