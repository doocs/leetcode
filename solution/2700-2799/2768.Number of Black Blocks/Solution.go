func countBlackBlocks(m int, n int, coordinates [][]int) []int64 {
	cnt := map[int64]int{}
	dirs := [5]int{0, 0, -1, -1, 0}
	for _, e := range coordinates {
		x, y := e[0], e[1]
		for k := 0; k < 4; k++ {
			i, j := x+dirs[k], y+dirs[k+1]
			if i >= 0 && i < m-1 && j >= 0 && j < n-1 {
				cnt[int64(i*n+j)]++
			}
		}
	}
	ans := make([]int64, 5)
	ans[0] = int64((m - 1) * (n - 1))
	for _, x := range cnt {
		ans[x]++
		ans[0]--
	}
	return ans
}