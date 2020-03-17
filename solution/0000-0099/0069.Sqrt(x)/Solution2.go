func mySqrt(x int) int {
	precision := 0.9 // 计算精度, 但 LeetCode 中要求返回 int 所以可以偷懒将误差 < 0.9 即可(不使用 1.0 是因为浮点型的精度问题), 追求精度可使用 0.0001
	guess, check := 1.0, 0.0
calc:
	guess = (float64(x)/guess + guess) / 2
	check = guess*guess - float64(x)
	if check < 0 {
		check = 0 - check
	}
	if check > precision {
		goto calc
	}
	return int(guess)
}
