impl Solution {
    pub fn find_the_winner(n: i32, k: i32) -> i32 {
        if n == 1 {
            return 1;
        }
		let mut ans = (k + Solution::find_the_winner(n - 1, k)) % n;
		return if ans == 0 { n } else { ans };
    }
}
