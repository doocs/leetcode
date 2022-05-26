func minSwaps(nums []int) int {
	cnt := 0
	for _, v := range nums {
		cnt += v
	}
	n := len(nums)
	s := make([]int, (n<<1)+1)
	for i := 0; i < (n << 1); i++ {
		s[i+1] = s[i] + nums[i%n]
	}
	mx := 0
	for i := 0; i < (n << 1); i++ {
		j := i + cnt - 1
		if j < (n << 1) {
			mx = max(mx, s[j+1]-s[i])
		}
	}
	return cnt - mx
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}