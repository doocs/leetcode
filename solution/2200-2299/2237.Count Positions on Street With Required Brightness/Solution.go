func meetRequirement(n int, lights [][]int, requirement []int) (ans int) {
	d := make([]int, n+1)
	for _, e := range lights {
		i, j := max(0, e[0]-e[1]), min(n-1, e[0]+e[1])
		d[i]++
		d[j+1]--
	}
	s := 0
	for i, r := range requirement {
		s += d[i]
		if s >= r {
			ans++
		}
	}
	return
}
