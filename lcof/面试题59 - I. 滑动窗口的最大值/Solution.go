func maxSlidingWindow(nums []int, k int) []int {
	ans := make([]int, 0, len(nums)-k+1)
	window := make([]int, 0)
	for i, num := range nums {
		for len(window) != 0 && nums[window[len(window)-1]] <= num {
			window = window[:len(window)-1]
		}
		window = append(window, i)
		if window[0] == i-k {
			window = window[1:]
		}
		if i >= k-1 {
			ans = append(ans, nums[window[0]])
		}
	}
	return ans
}
