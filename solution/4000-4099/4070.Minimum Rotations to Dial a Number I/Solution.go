func minRotations(s string) int {
	ans := 0
	pre := 0
	for i := 0; i < len(s); i++ {
		cur := int(s[i] - '0')
		diff := abs(cur - pre)
		ans += min(diff, 10-diff)
		pre = cur
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
