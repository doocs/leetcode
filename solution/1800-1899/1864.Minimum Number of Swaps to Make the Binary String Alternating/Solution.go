func minSwaps(s string) int {
	n0 := strings.Count(s, "0")
	n1 := len(s) - n0
	if abs(n0-n1) > 1 {
		return -1
	}
	calc := func(c int) int {
		cnt := 0
		for i, ch := range s {
			x := int(ch - '0')
			if i&1^c != x {
				cnt++
			}
		}
		return cnt / 2
	}
	if n0 == n1 {
		return min(calc(0), calc(1))
	}
	if n0 > n1 {
		return calc(0)
	}
	return calc(1)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
