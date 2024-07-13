func decode(encoded []int, first int) []int {
	ans := []int{first}
	for i, x := range encoded {
		ans = append(ans, ans[i]^x)
	}
	return ans
}