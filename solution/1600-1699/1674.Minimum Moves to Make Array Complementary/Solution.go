func minMoves(nums []int, limit int) int {
	n := len(nums)
	d := make([]int, 2*limit+2)
	for i := 0; i < n/2; i++ {
		x, y := nums[i], nums[n-1-i]
		if x > y {
			x, y = y, x
		}
		d[2] += 2
		d[x+1] -= 2
		d[x+1] += 1
		d[x+y] -= 1
		d[x+y+1] += 1
		d[y+limit+1] -= 1
		d[y+limit+1] += 2
	}
	ans, s := n, 0
	for _, x := range d[2:] {
		s += x
		ans = min(ans, s)
	}
	return ans
}