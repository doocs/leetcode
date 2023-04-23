func supplyWagon(supplies []int) []int {
	for h := (len(supplies) + 1) >> 1; h > 0; h-- {
		n := len(supplies)
		mi := 1 << 30
		k := 0
		for i := 0; i < n-1; i++ {
			x := supplies[i] + supplies[i+1]
			if mi > x {
				mi = x
				k = i
			}
		}
		t := make([]int, n-1)
		for i, j := 0, 0; i < n; i, j = i+1, j+1 {
			if i == k {
				t[j] = mi
				i++
			} else {
				t[j] = supplies[i]
			}
		}
		supplies = t
	}
	return supplies
}