func getMaximumXor(nums []int, maximumBit int) []int {
	n := len(nums)
	s := make([]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] ^ v
	}
	var ans []int
	for i := n; i > 0; i-- {
		t, x := 0, s[i]
		for j := 0; j < maximumBit; j++ {
			if ((x >> j) & 1) == 0 {
				t |= (1 << j)
			}
		}
		ans = append(ans, t)
	}
	return ans
}