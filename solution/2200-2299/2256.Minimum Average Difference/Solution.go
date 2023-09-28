func minimumAverageDifference(nums []int) (ans int) {
	n := len(nums)
	pre, suf := 0, 0
	for _, x := range nums {
		suf += x
	}
	mi := suf
	for i, x := range nums {
		pre += x
		suf -= x
		a := pre / (i + 1)
		b := 0
		if n-i-1 != 0 {
			b = suf / (n - i - 1)
		}
		if t := abs(a - b); t < mi {
			ans = i
			mi = t
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}