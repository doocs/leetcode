func minimumDistance(nums []int) int {
	g := make(map[int][]int)
	for i, x := range nums {
		g[x] = append(g[x], i)
	}

	inf := 1 << 30
	ans := inf

	for _, ls := range g {
		m := len(ls)
		for h := 0; h < m-2; h++ {
			i := ls[h]
			k := ls[h+2]
			t := (k - i) * 2
			ans = min(ans, t)
		}
	}

	if ans == inf {
		return -1
	}
	return ans
}
