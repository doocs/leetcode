func canChange(start string, target string) bool {
	f := func(s string) [][]int {
		res := [][]int{}
		for i, c := range s {
			if c == 'L' {
				res = append(res, []int{1, i})
			} else if c == 'R' {
				res = append(res, []int{2, i})
			}
		}
		return res
	}

	a, b := f(start), f(target)
	if len(a) != len(b) {
		return false
	}
	for i, x := range a {
		y := b[i]
		if x[0] != y[0] {
			return false
		}
		if x[0] == 1 && x[1] < y[1] {
			return false
		}
		if x[0] == 2 && x[1] > y[1] {
			return false
		}
	}
	return true
}