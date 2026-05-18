func countKthRoots(l int, r int, k int) int {
	if k == 1 {
		return r - l + 1
	}
	ans := 0
	for x := 0; ; x++ {
		y := 1
		for i := 0; i < k; i++ {
			y *= x
			if y > r {
				break
			}
		}
		if y > r {
			break
		}
		if l <= y && y <= r {
			ans++
		}
	}
	return ans
}