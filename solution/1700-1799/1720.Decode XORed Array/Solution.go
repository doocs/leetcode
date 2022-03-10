func decode(encoded []int, first int) []int {
	ans := []int{first}
	for i, e := range encoded {
		ans = append(ans, ans[i]^e)
	}
	return ans
}