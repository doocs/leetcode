func addToArrayForm(num []int, k int) []int {
	i, carry := len(num)-1, 0
	ans := []int{}
	for ; i >= 0 || k > 0 || carry > 0; i-- {
		if i >= 0 {
			carry += num[i]
		}
		carry += k % 10
		ans = append(ans, carry%10)
		carry /= 10
		k /= 10
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return ans
}