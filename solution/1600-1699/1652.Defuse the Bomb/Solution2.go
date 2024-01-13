func decrypt(code []int, k int) []int {
	n := len(code)
	ans := make([]int, n)
	if k == 0 {
		return ans
	}
	s := make([]int, n<<1|1)
	for i := 0; i < n<<1; i++ {
		s[i+1] = s[i] + code[i%n]
	}
	for i := range code {
		if k > 0 {
			ans[i] = s[i+k+1] - s[i+1]
		} else {
			ans[i] = s[i+n] - s[i+k+n]
		}
	}
	return ans
}