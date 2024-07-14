func fractionToDecimal(numerator int, denominator int) string {
	if numerator == 0 {
		return "0"
	}
	ans := ""
	if (numerator > 0) != (denominator > 0) {
		ans += "-"
	}
	a := int64(numerator)
	b := int64(denominator)
	a = abs(a)
	b = abs(b)
	ans += strconv.FormatInt(a/b, 10)
	a %= b
	if a == 0 {
		return ans
	}
	ans += "."
	d := make(map[int64]int)
	for a != 0 {
		if pos, ok := d[a]; ok {
			ans = ans[:pos] + "(" + ans[pos:] + ")"
			break
		}
		d[a] = len(ans)
		a *= 10
		ans += strconv.FormatInt(a/b, 10)
		a %= b
	}
	return ans
}

func abs(x int64) int64 {
	if x < 0 {
		return -x
	}
	return x
}