func perfectMenu(materials []int, cookbooks [][]int, attribute [][]int, limit int) int {
	n := len(cookbooks)
	ans := -1
	for mask := 0; mask < 1<<n; mask++ {
		a, b := 0, 0
		cnt := make([]int, 5)
		for i := 0; i < n; i++ {
			if mask>>i&1 == 1 {
				x, y := attribute[i][0], attribute[i][1]
				a += x
				b += y
				for j, v := range cookbooks[i] {
					cnt[j] += v
				}
			}
			ok := true
			for i := 0; i < 5 && ok; i++ {
				ok = cnt[i] <= materials[i]
			}
			if ok && b >= limit && ans < a {
				ans = a
			}
		}
	}
	return ans
}