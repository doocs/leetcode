/**
 * @lc app=leetcode.cn id=26 lang=golang
 * Accepted
 * 161/161 cases passed (144 ms)
 * Your runtime beats 36.91 % of golang submissions
 * Your memory usage beats 40.4 % of golang submissions (8.2 MB)
 */

func removeDuplicates(nums []int) int {
	if nums == nil || len(nums) == 0 {
		return 0
	}
	j := 0
	for i := 1; i < len(nums); i++ {
		if nums[j] != nums[i] {
			j++
			nums[j] = nums[i]
		}
	}
	return j + 1
}
