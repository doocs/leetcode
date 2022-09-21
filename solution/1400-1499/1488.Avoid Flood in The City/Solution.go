func avoidFlood(rains []int) []int {
	n := len(rains)
	ans := make([]int, n)
	for i := range ans {
		ans[i] = -1
	}
	sunny := []int{}
	rainy := map[int]int{}
	for i, v := range rains {
		if v > 0 {
			if j, ok := rainy[v]; ok {
				idx := sort.Search(len(sunny), func(i int) bool { return sunny[i] > j })
				if idx == len(sunny) {
					return []int{}
				}
				ans[sunny[idx]] = v
				sunny = append(sunny[:idx], sunny[idx+1:]...)
			}
			rainy[v] = i
		} else {
			sunny = append(sunny, i)
			ans[i] = 1
		}
	}
	return ans
}