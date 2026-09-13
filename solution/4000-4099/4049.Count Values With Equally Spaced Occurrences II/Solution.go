func countSpecialIntegers(nums []int) int {
	g := make(map[int][]int)
	for i, x := range nums {
		g[x] = append(g[x], i)
	}

	ans := 0
	for _, pos := range g {
		if len(pos) < 3 {
			continue
		}

		d := pos[1] - pos[0]
		ok := true
		for i := 1; i < len(pos); i++ {
			if pos[i]-pos[i-1] != d {
				ok = false
				break
			}
		}

		if ok {
			ans++
		}
	}
	return ans
}
