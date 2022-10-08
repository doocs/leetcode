func minMoves(nums []int, limit int) int {
	d := make([]int, limit*2+2)
	n := len(nums)
	for i := 0; i < n>>1; i++ {
		a, b := min(nums[i], nums[n-i-1]), max(nums[i], nums[n-i-1])
		d[2] += 2
		d[limit*2+1] -= 2

		d[a+1] -= 1
		d[b+limit+1] += 1

		d[a+b] -= 1
		d[a+b+1] += 1
	}
	ans, s := n, 0
	for _, v := range d[2 : limit*2+1] {
		s += v
		if ans > s {
			ans = s
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}