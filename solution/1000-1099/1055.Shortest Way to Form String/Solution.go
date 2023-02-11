func shortestWay(source string, target string) int {
	m, n := len(source), len(target)
	ans, j := 0, 0
	for j < n {
		ok := false
		for i := 0; i < m && j < n; i++ {
			if source[i] == target[j] {
				ok = true
				j++
			}
		}
		if !ok {
			return -1
		}
		ans++
	}
	return ans
}