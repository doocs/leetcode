func maxScoreIndices(nums []int) []int {
	left, right := 0, 0
	for _, num := range nums {
		right += num
	}
	mx := right
	ans := []int{0}
	for i, num := range nums {
		if num == 0 {
			left++
		} else {
			right--
		}
		t := left + right
		if mx == t {
			ans = append(ans, i+1)
		} else if mx < t {
			mx = t
			ans = []int{i + 1}
		}
	}
	return ans
}