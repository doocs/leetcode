func strToInt(str string) int {
	n, sign, i, x := len(str), 1, 0, 0

	// 去除开头的空格
	for i < n && str[i] == ' ' {
		i++
	}

	// 如果只有空格
	if i == n {
		return 0
	}

	// 负数
	if str[i] == '-' {
		sign = -1
	}

	// 跳过符号
	if str[i] == '-' || str[i] == '+' {
		i++
	}

	// 如果符号后紧跟的不是数字
	if i == n || str[i] < '0' || str[i] > '9' {
		return 0
	}

	// golang 在 64 位平台下 int 就是 int64，所以不用对 x 进行特殊处理
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
