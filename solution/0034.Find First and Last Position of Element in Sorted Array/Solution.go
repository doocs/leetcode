/**
 * @lc app=leetcode.cn id=34 lang=golang
 * Accepted
 * 88/88 cases passed (12 ms)
 * Your runtime beats 90.75 % of golang submissions
 * Your memory usage beats 60.76 % of golang submissions (4.1 MB)
 */
func searchRange(nums []int, target int) []int {
	l, m, r := 0, 0, len(nums)-1
	for l <= r {
		m = (l + r) / 2
		if nums[m] == target {
			return []int{findFirst(nums, l, m, target), findEnd(nums, m, r, target)}
		}
		if nums[m] < target {
			l = m + 1
		} else if nums[m] > target {
			r = m - 1
		}
	}
	return []int{-1, -1}
}

func findFirst(nums []int, l, r, target int) int {
	var m int
	for l < r {
		m = (l + r) / 2
		if nums[m] < target {
			l = m + 1
		} else if nums[m] == target {
			r = m
		}
	}
	return l
}
func findEnd(nums []int, l, r, target int) int {
	var m int
	for l < r {
		m = (l + r + 1) / 2 // 需要加一，否则陷入死循环
		if nums[m] > target {
			r = m - 1
		} else if nums[m] == target {
			l = m
		}
	}
	return l
}

