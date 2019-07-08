/**
 * @lc app=leetcode.cn id=69 lang=golang
 * Accepted
 * 1017/1017 cases passed (0 ms)
 * Your runtime beats 100 % of golang submissions
 * Your memory usage beats 25.49 % of golang submissions (2.2 MB)
 */

func mySqrt(x int) int {
	if x == 0 || x == 1 {
		return x
	}
	l, r, t := 1, x, 0
	m := (l + r) / 2
	for l != m {
		t = m * m
		if t == x {
			return m
		}
		if t < x {
			l = m
		} else {
			r = m
		}
		m = (l + r) / 2
	}
	return m
}