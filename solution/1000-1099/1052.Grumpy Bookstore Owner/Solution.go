func maxSatisfied(customers []int, grumpy []int, minutes int) int {
	s, cs := 0, 0
	for i, c := range customers {
		s += c * grumpy[i]
		cs += c
	}
	t, ans := 0, 0
	for i, c := range customers {
		t += c * grumpy[i]
		j := i - minutes + 1
		if j >= 0 {
			ans = max(ans, cs-(s-t))
			t -= customers[j] * grumpy[j]
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}