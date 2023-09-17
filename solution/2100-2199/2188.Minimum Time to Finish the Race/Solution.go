func minimumFinishTime(tires [][]int, changeTime int, numLaps int) int {
	const inf = 1 << 30
	cost := [18]int{}
	for i := range cost {
		cost[i] = inf
	}
	for _, e := range tires {
		f, r := e[0], e[1]
		s, t := 0, f
		for i := 1; t <= changeTime+f; i++ {
			s += t
			cost[i] = min(cost[i], s)
			t *= r
		}
	}
	f := make([]int, numLaps+1)
	for i := range f {
		f[i] = inf
	}
	f[0] = -changeTime
	for i := 1; i <= numLaps; i++ {
		for j := 1; j < min(18, i+1); j++ {
			f[i] = min(f[i], f[i-j]+cost[j])
		}
		f[i] += changeTime
	}
	return f[numLaps]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}