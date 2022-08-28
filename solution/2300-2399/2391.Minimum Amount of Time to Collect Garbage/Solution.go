func garbageCollection(garbage []string, travel []int) int {
	n := len(garbage)
	f := func(c rune) int {
		tot := 0
		for _, v := range garbage {
			for _, ch := range v {
				if ch == c {
					tot++
				}
			}
		}
		res := 0
		for i, v := range garbage {
			t := 0
			for _, ch := range v {
				if ch == c {
					t++
				}
			}
			res += t
			tot -= t
			if tot == 0 {
				break
			}
			if i < n-1 {
				res += travel[i]
			}
		}
		return res
	}
	return f('M') + f('P') + f('G')
}