func spiralMatrixIII(rows int, cols int, rStart int, cStart int) [][]int {
	cnt := rows * cols
	ans := [][]int{[]int{rStart, cStart}}
	if cnt == 1 {
		return ans
	}
	for k := 1; ; k += 2 {
		dirs := [][]int{{0, 1, k}, {1, 0, k}, {0, -1, k + 1}, {-1, 0, k + 1}}
		for _, dir := range dirs {
			r, c, dk := dir[0], dir[1], dir[2]
			for dk > 0 {
				rStart += r
				cStart += c
				if rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols {
					ans = append(ans, []int{rStart, cStart})
					if len(ans) == cnt {
						return ans
					}
				}
				dk--
			}
		}
	}
}