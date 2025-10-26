func maxSumOfSquares(num int, sum int) string {
	if num*9 < sum {
		return ""
	}

	k, s := sum/9, sum%9
	ans := strings.Repeat("9", k)
	if s > 0 {
		ans += string('0' + byte(s))
	}
	if len(ans) < num {
		ans += strings.Repeat("0", num-len(ans))
	}

	return ans
}
