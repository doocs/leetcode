func addToArrayForm(num []int, k int) (ans []int) {
	for i := len(num) - 1; i >= 0 || k > 0; i-- {
		if i >= 0 {
			k += num[i]
		}
		ans = append(ans, k%10)
		k /= 10
	}
	slices.Reverse(ans)
	return
}
