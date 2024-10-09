func maxScoreIndices(nums []int) []int {
	l0, r1 := 0, 0
	for _, x := range nums {
		r1 += x
	}
	mx := r1
	ans := []int{0}
	for i, x := range nums {
		l0 += x ^ 1
		r1 -= x
		t := l0 + r1
		if mx == t {
			ans = append(ans, i+1)
		} else if mx < t {
			mx = t
			ans = []int{i + 1}
		}
	}
	return ans
}