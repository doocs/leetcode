func findDifferentBinaryString(nums []string) string {
	ans := make([]byte, len(nums))
	for i, s := range nums {
		if s[i] == '0' {
			ans[i] = '1'
		} else {
			ans[i] = '0'
		}
	}
	return string(ans)
}
