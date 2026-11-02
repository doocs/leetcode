func strToInt(str string) int {
	n, sign, i, x := len(str), 1, 0, 0

	for i < n && str[i] == ' ' {
		i++
	}

	if i == n {
		return 0
	}

	if str[i] == '-' {
		sign = -1
	}

	if str[i] == '-' || str[i] == '+' {
		i++
	}

	if i == n || str[i] < '0' || str[i] > '9' {
		return 0
	}

	for ; i < n && str[i] >= '0' && str[i] <= '9'; i++ {
		x = x*10 + int(str[i]) - '0'
		if x > math.MaxInt32 {
			break
		}
	}

	if x > math.MaxInt32 {
		if sign == 1 {
			return math.MaxInt32
		}
		return math.MinInt32
	}
	return sign * x
}
