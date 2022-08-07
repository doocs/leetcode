func mergeSimilarItems(items1 [][]int, items2 [][]int) [][]int {
	cnt := make([]int, 1010)
	for _, e := range items1 {
		cnt[e[0]] += e[1]
	}
	for _, e := range items2 {
		cnt[e[0]] += e[1]
	}
	ans := [][]int{}
	for i, v := range cnt {
		if v > 0 {
			ans = append(ans, []int{i, v})
		}
	}
	return ans
}