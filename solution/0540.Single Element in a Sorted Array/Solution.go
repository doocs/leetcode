/**
 * @lc app=leetcode.cn id=540 lang=golang
 * Accepted
 * 12/12 cases passed (20 ms)
 * Your runtime beats 17.65 % of golang submissions
 * Your memory usage beats 9.09 % of golang submissions (4.1 MB)
 */
func singleNonDuplicate(nums []int) int {
	l, m, r := 0, 0, len(nums)-1
	for l < r {
		m = (r-l)/2 + l
		if m%2 == 1 {
			m--
		}
		if nums[m] == nums[m+1] {
			l = m + 2
		} else {
			r = m
		}
	}
	return nums[r]
}