func divide(a int, b int) int {
	sign, ans, INT32_MAX, INT32_MIN, LIMIT := false, 0, 1<<31-1, -1<<31, -1<<31/2
	if (a > 0 && b < 0) || (a < 0 && b > 0) {
		sign = true
	}
	a, b = convert(a), convert(b)
	for a <= b {
		cnt := 0
		// (b<<cnt) >= LIMIT 是为了避免 b<<(cnt+1) 发生溢出
		for (b<<cnt) >= LIMIT && a <= (b<<(cnt+1)) {
			cnt++
		}
		ans = ans + -1<<cnt
		a = a - b<<cnt
	}
	if sign {
		return ans
	}
	if ans == INT32_MIN {
		return INT32_MAX
	}
	return -ans
}

func convert(v int) int {
	if v > 0 {
		return -v
	}
	return v
}
