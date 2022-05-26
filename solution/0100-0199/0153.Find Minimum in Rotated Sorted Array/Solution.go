/**
 * @lc app=leetcode.cn id=153 lang=golang
 * Accepted
 * 146/146 cases passed (4 ms)
 * Your runtime beats 93 % of golang submissions
 * Your memory usage beats 40.91 % of golang submissions (2.6 MB)
 */
func findMin(nums []int) int {
	l, m, r := 0, 0, len(nums)-1
	for l < r {
		if nums[l] <= nums[r] {
			return nums[l]
		}
		m = (r + l) / 2
		if nums[l] <= nums[m] {
			l = m + 1
		} else {
			r = m
		}

	}
	return nums[l]
}