func totalHammingDistance(nums []int) (ans int) {
	for i := 0; i < 32; i++ {
		a := 0
		for _, x := range nums {
			a += x >> i & 1
		}
		b := len(nums) - a
		ans += a * b
	}
	return
}