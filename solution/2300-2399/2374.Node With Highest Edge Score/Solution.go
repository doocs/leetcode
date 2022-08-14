func edgeScore(edges []int) int {
	n := len(edges)
	cnt := make([]int, n)
	for i, v := range edges {
		cnt[v] += i
	}
	ans := 0
	for i, v := range cnt {
		if cnt[ans] < v {
			ans = i
		}
	}
	return ans
}