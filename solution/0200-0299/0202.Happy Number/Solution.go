var resultMap map[int]bool

func init() {
	resultMap = make(map[int]bool)
}

func calculate(num int) int {
	sum := 0
	for num > 0 {
		k := num % 10
		sum += k * k
		num = num / 10
	}
	return sum
}

func isHappy(n int) bool {
	tmpMap := make(map[int]bool)
	for ; ; n = calculate(n) {
		f, ok := resultMap[n]
		if f || n == 1 {
			for k := range tmpMap {
				resultMap[k] = true
			}
			return true
		}
		if (!f && ok) || tmpMap[n] {
			for k := range tmpMap {
				resultMap[k] = false
			}
			return false
		}
		tmpMap[n] = true
	}
}
