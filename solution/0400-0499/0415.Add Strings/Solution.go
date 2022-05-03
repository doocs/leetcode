func addStrings(num1 string, num2 string) string {
	ans := ""
	i, j, carry := len(num1)-1, len(num2)-1, 0
	for ; i >= 0 || j >= 0 || carry != 0; i, j = i-1, j-1 {
		if i >= 0 {
			carry += int(num1[i] - '0')
		}
		if j >= 0 {
			carry += int(num2[j] - '0')
		}
		ans = strconv.Itoa(carry%10) + ans
		carry /= 10
	}
	return ans
}