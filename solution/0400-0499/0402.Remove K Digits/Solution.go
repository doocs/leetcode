func removeKdigits(num string, k int) string {
	stack, remain := make([]byte, 0), len(num)-k
	for i := 0; i < len(num); i++ {
		n := len(stack)
		for k > 0 && n > 0 && stack[n-1] > num[i] {
			stack = stack[:n-1]
			n, k = n-1, k-1
		}
		stack = append(stack, num[i])
	}
	// 返回删除 k 个字符之后的字符串，需要去除可能存在的前置 0
	for i := 0; i < len(stack) && i < remain; i++ {
		if stack[i] != '0' {
			return string(stack[i:remain])
		}
	}
	return "0"
}