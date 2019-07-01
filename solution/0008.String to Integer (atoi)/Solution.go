func myAtoi(str string) int {
	cer := 0
	result := 0
    tmpResult := 0
	flag := false
	for _, n := range str {
		if !flag && n == ' ' {
			continue
		}
		flag = true
		if cer == 0 {
			if n >= '0' && n <= '9' {
				cer = 1
			} else if n == '+' {
				cer = 1
				continue
			} else if cer == 0 && (n == '-') {
				cer = -1
				continue
			}
		}

		if n >= '0' && n <= '9' {
			tmpResult = tmpResult * 10 + ((int)(n) - 48)
            result = cer * tmpResult
		} else {
			break
		}
        if result < math.MinInt32 {
		    return math.MinInt32
        }
        if result > math.MaxInt32 {
            return math.MaxInt32
        }
	}
	return result
}