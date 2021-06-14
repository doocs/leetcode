/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * func guess(num int) int;
 */

 func guessNumber(n int) int {
    low, high := 1, n
    for low < high {
        mid := (low + high) >> 1
        if guess(mid) <= 0 {
            high = mid
        } else {
            low = mid + 1
        }
    }
    return low
}