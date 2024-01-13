func maximumSumOfHeights(maxHeights []int) (ans int64) {
	n := len(maxHeights)
	stk := []int{}
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i] = -1
		right[i] = n
	}
	for i, x := range maxHeights {
		for len(stk) > 0 && maxHeights[stk[len(stk)-1]] > x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		x := maxHeights[i]
		for len(stk) > 0 && maxHeights[stk[len(stk)-1]] >= x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			right[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	f := make([]int64, n)
	g := make([]int64, n)
	for i, x := range maxHeights {
		if i > 0 && x >= maxHeights[i-1] {
			f[i] = f[i-1] + int64(x)
		} else {
			j := left[i]
			f[i] = int64(x) * int64(i-j)
			if j != -1 {
				f[i] += f[j]
			}
		}
	}
	for i := n - 1; i >= 0; i-- {
		x := maxHeights[i]
		if i < n-1 && x >= maxHeights[i+1] {
			g[i] = g[i+1] + int64(x)
		} else {
			j := right[i]
			g[i] = int64(x) * int64(j-i)
			if j != n {
				g[i] += g[j]
			}
		}
	}
	for i, x := range maxHeights {
		ans = max(ans, f[i]+g[i]-int64(x))
	}
	return
}