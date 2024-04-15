func minOperations(nums []int, x int) int {
	s := -x
	for _, v := range nums {
		s += v
	}
	mx, t, j := -1, 0, 0
	for i, v := range nums {
		t += v
		for ; j <= i && t > s; j++ {
			t -= nums[j]
		}
		if t == s {
			mx = max(mx, i-j+1)
		}
	}
	if mx == -1 {
		return -1
	}
	return len(nums) - mx
}