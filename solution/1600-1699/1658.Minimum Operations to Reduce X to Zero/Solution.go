func minOperations(nums []int, x int) int {
	x = -x
	for _, v := range nums {
		x += v
	}
	ans := 1 << 30
	s, n := 0, len(nums)
	j := 0
	for i, v := range nums {
		s += v
		for j <= i && s > x {
			s -= nums[j]
			j++
		}
		if s == x {
			ans = min(ans, n-(i-j+1))
		}
	}
	if ans == 1<<30 {
		return -1
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}