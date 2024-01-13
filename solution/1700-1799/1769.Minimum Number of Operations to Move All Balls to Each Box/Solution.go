func minOperations(boxes string) []int {
	n := len(boxes)
	left := make([]int, n)
	right := make([]int, n)
	for i, cnt := 1, 0; i < n; i++ {
		if boxes[i-1] == '1' {
			cnt++
		}
		left[i] = left[i-1] + cnt
	}
	for i, cnt := n-2, 0; i >= 0; i-- {
		if boxes[i+1] == '1' {
			cnt++
		}
		right[i] = right[i+1] + cnt
	}
	ans := make([]int, n)
	for i := range ans {
		ans[i] = left[i] + right[i]
	}
	return ans
}