var p []int

func minimumHammingDistance(source []int, target []int, allowedSwaps [][]int) int {
	n := len(source)
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	for _, e := range allowedSwaps {
		p[find(e[0])] = find(e[1])
	}
	mp := make(map[int]map[int]int)
	for i := 0; i < n; i++ {
		if mp[find(i)] == nil {
			mp[find(i)] = make(map[int]int)
		}
		mp[find(i)][source[i]]++
	}
	res := 0
	for i := 0; i < n; i++ {
		if mp[find(i)][target[i]] > 0 {
			mp[find(i)][target[i]]--
		} else {
			res++
		}
	}
	return res
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}