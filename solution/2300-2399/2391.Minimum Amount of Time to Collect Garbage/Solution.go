func garbageCollection(garbage []string, travel []int) (ans int) {
	last := map[byte]int{}
	for i, s := range garbage {
		ans += len(s)
		for j := range s {
			last[s[j]] = i
		}
	}
	ts := 0
	for i := 1; i <= len(travel); i++ {
		ts += travel[i-1]
		for _, j := range last {
			if i == j {
				ans += ts
			}
		}
	}
	return
}