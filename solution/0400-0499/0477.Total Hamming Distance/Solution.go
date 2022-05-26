func totalHammingDistance(nums []int) int {
	ans := 0
	for i := 0; i < 31; i++ {
		a, b := 0, 0
		for _, v := range nums {
			t := (v >> i) & 1
			a += t
			b += t ^ 1
		}
		ans += a * b
	}
	return ans
}