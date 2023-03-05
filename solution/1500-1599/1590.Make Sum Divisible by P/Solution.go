func minSubarray(nums []int, p int) int {
	k := 0
	for _, x := range nums {
		k = (k + x) % p
	}
	if k == 0 {
		return 0
	}
	last := map[int]int{0: -1}
	n := len(nums)
	ans := n
	cur := 0
	for i, x := range nums {
		cur = (cur + x) % p
		target := (cur - k + p) % p
		if j, ok := last[target]; ok {
			ans = min(ans, i-j)
		}
		last[cur] = i
	}
	if ans == n {
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