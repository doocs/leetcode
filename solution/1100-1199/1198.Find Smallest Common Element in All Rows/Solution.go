func smallestCommonElement(mat [][]int) int {
	cnt := [10001]int{}
	for _, row := range mat {
		for _, x := range row {
			cnt[x]++
			if cnt[x] == len(mat) {
				return x
			}
		}
	}
	return -1
}