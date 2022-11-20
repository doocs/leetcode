func minCostII(costs [][]int) (ans int) {
	n, k := len(costs), len(costs[0])
	f := cp(costs[0])
	for i := 1; i < n; i++ {
		g := cp(costs[i])
		for j := 0; j < k; j++ {
			t := math.MaxInt32
			for h := 0; h < k; h++ {
				if h != j && t > f[h] {
					t = f[h]
				}
			}
			g[j] += t
		}
		f = g
	}
	ans = f[0]
	for _, v := range f {
		if ans > v {
			ans = v
		}
	}
	return
}

func cp(arr []int) []int {
	t := make([]int, len(arr))
	copy(t, arr)
	return t
}