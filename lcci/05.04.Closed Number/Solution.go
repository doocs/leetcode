func findClosedNumbers(num int) []int {
	ans := []int{-1, -1}
	dirs := [3]int{0, 1, 0}
	for p := 0; p < 2; p++ {
		a, b := dirs[p], dirs[p+1]
		x := num
		for i := 1; i < 31; i++ {
			if x>>i&1 == a && x>>(i-1)&1 == b {
				x ^= 1 << i
				x ^= 1 << (i - 1)
				j, k := 0, i-2
				for j < k {
					for j < k && x>>j&1 == b {
						j++
					}
					for j < k && x>>k&1 == a {
						k--
					}
					if j < k {
						x ^= 1 << j
						x ^= 1 << k
					}
				}
				ans[p] = x
				break
			}
		}
	}
	return ans
}