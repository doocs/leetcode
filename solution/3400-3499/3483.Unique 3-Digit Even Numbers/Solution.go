func totalNumbers(digits []int) int {
	s := make(map[int]struct{})
	for i, a := range digits {
		if a%2 == 1 {
			continue
		}
		for j, b := range digits {
			if i == j {
				continue
			}
			for k, c := range digits {
				if c == 0 || k == i || k == j {
					continue
				}
				s[c*100+b*10+a] = struct{}{}
			}
		}
	}
	return len(s)
}
