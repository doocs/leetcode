func findContinuousSequence(target int) [][]int {
	ans := make([][]int, 0)
	window := 0
	left, right := 1, 1
	for n := target / 2; left <= n; {
		if window < target {
			window += right
			right++
		} else if window > target {
			window -= left
			left++
		} else {
			tmp := make([]int, 0, right-left)
			for i := left; i < right; i++ {
				tmp = append(tmp, i)
			}
			ans = append(ans, tmp)
			window -= left
			left++
		}
	}
	return ans
}
