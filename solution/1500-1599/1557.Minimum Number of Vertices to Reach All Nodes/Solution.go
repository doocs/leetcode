func findSmallestSetOfVertices(n int, edges [][]int) (ans []int) {
	cnt := make([]int, n)
	for _, e := range edges {
		cnt[e[1]]++
	}
	for i, c := range cnt {
		if c == 0 {
			ans = append(ans, i)
		}
	}
	return
}