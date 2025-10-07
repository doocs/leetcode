func avoidFlood(rains []int) []int {
	n := len(rains)
	ans := make([]int, n)
	for i := range ans {
		ans[i] = -1
	}

	sunny := redblacktree.New[int, struct{}]()
	rainy := map[int]int{}

	for i, v := range rains {
		if v > 0 {
			if last, ok := rainy[v]; ok {
				node, found := sunny.Ceiling(last + 1)
				if !found {
					return []int{}
				}
				t := node.Key
				ans[t] = v
				sunny.Remove(t)
			}
			rainy[v] = i
		} else {
			sunny.Put(i, struct{}{})
			ans[i] = 1
		}
	}
	return ans
}
