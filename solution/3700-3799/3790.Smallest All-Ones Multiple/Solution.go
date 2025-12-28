func minAllOneMultiple(k int) int {
	if k&1 == 0 {
		return -1
	}

	x := 1 % k
	ans := 1

	for i := 0; i < k; i++ {
		x = (x*10 + 1) % k
		ans++
		if x == 0 {
			return ans
		}
	}

	return -1
}
