func findSmallestSetOfVertices(n int, edges [][]int) []int {
	s := make(map[int]bool)
	for _, e := range edges {
		s[e[1]] = true
	}
	var ans []int
	for i := 0; i < n; i++ {
		if !s[i] {
			ans = append(ans, i)
		}
	}
	return ans
}