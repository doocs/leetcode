/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * unsafe fn guess(num: i32) -> i32 {}
 */

impl Solution {
    unsafe fn guessNumber(n: i32) -> i32 {
        let mut l = 1;
        let mut r = n;
        loop {
            let mid = l + (r - l) / 2;
            match guess(mid) {
                -1 => r = mid - 1,
                1 => l = mid + 1,
                _ => break mid,
            }
        }
    }
}
