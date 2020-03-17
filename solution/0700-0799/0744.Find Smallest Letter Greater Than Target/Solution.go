/**
 * @lc app=leetcode.cn id=744 lang=golang
 * Accepted
 * 165/165 cases passed (4 ms)
 * Your runtime beats 74.14 % of golang submissions
 * Your memory usage beats 45.45 % of golang submissions (3 MB)
 */
func nextGreatestLetter(letters []byte, target byte) byte {
	l, m, r := 0, 0, len(letters)
	for l < r {
		m = (r + l) / 2
		if letters[m] <= target {
			l = m + 1
		} else {
			r = m
		}
	}
	return letters[l%len(letters)]
}