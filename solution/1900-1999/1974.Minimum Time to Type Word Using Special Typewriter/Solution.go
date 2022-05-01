func minTimeToType(word string) int {
	ans, prev := 0, 0
	for _, c := range word {
		curr := int(c - 'a')
		t := abs(prev - curr)
		t = min(t, 26-t)
		ans += t + 1
		prev = curr
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}