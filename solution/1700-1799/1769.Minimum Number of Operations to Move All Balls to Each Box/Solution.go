func minOperations(boxes string) []int {
	n := len(boxes)
	res := make([]int, n)
	total := 0
	for i, b := range boxes {
		if b == '1' {
			res[0] += i
			total++
		}
	}
	left, right := 0, total
	for i := 1; i < n; i++ {
		if boxes[i-1] == '1' {
			left++
			right--
		}
		res[i] = res[i-1] + left - right
	}
	return res
}