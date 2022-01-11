func isEscapePossible(blocked [][]int, source []int, target []int) bool {
	const N = 1e6
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	block := make(map[int]bool)
	for _, b := range blocked {
		block[b[0]*N+b[1]] = true
	}
	var dfs func(source, target []int, seen map[int]bool) bool
	dfs = func(source, target []int, seen map[int]bool) bool {
		sx, sy := source[0], source[1]
		tx, ty := target[0], target[1]
		if sx < 0 || sx >= N || sy < 0 || sy >= N || tx < 0 || tx >= N || ty < 0 || ty >= N || block[sx*N+sy] || seen[sx*N+sy] {
			return false
		}
		seen[sx*N+sy] = true
		if len(seen) > 20000 || (sx == target[0] && sy == target[1]) {
			return true
		}
		for _, dir := range dirs {
			next := []int{sx + dir[0], sy + dir[1]}
			if dfs(next, target, seen) {
				return true
			}
		}
		return false
	}
	s1, s2 := make(map[int]bool), make(map[int]bool)
	return dfs(source, target, s1) && dfs(target, source, s2)
}