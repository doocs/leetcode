func minimumHammingDistance(source []int, target []int, allowedSwaps [][]int) (ans int) {
	n := len(source)
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, a := range allowedSwaps {
		p[find(a[0])] = find(a[1])
	}
	cnt := map[int]map[int]int{}
	for i, x := range source {
		j := find(i)
		if cnt[j] == nil {
			cnt[j] = map[int]int{}
		}
		cnt[j][x]++
	}
	for i, x := range target {
		j := find(i)
		cnt[j][x]--
		if cnt[j][x] < 0 {
			ans++
		}
	}
	return
}