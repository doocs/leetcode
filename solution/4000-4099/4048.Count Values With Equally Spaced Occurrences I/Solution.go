func countSpecialIntegers(nums []int) int {
	g := make(map[int][]int)
	for i, x := range nums {
		g[x] = append(g[x], i)
	}

	ans := 0
	for _, pos := range g {
		if len(pos) == 3 && pos[0]+pos[2] == pos[1]*2 {
			ans++
		}
	}
	return ans
}
