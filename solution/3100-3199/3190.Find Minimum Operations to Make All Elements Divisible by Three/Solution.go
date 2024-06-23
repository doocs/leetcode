func minimumOperations(nums []int) (ans int) {
	for _, x := range nums {
		if mod := x % 3; mod > 0 {
			ans += min(mod, 3-mod)
		}
	}
	return
}