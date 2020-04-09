/*
 * @lc app=leetcode.cn id=75 lang=golang
 *  87/87 cases passed (4 ms)， memory usage 2.4 MB
 */
func sortColors(nums []int) {
	finish0 := -1       // 元素1结束的位置
	start2 := len(nums) // 元素2开始的位置
	for i := 0; i < start2; {
		if nums[i] == 0 {
			finish0++
			nums[i], nums[finish0] = nums[finish0], 0
			i++
		} else if nums[i] == 2 {
			start2--
			nums[i], nums[start2] = nums[start2], 2
		} else {
			i++
		}
	}
}
