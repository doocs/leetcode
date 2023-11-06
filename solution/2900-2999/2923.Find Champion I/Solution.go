func findChampion(grid [][]int) int {
	n := len(grid)
	for i := 0; ; i++ {
		cnt := 0
		for j, x := range grid[i] {
			if i != j && x == 1 {
				cnt++
			}
		}
		if cnt == n-1 {
			return i
		}
	}
}