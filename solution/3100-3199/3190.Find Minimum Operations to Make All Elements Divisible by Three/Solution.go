func minimumOperations(nums []int) (ans int) {
	for _, x := range nums {
		if x%3 != 0 {
			ans++
		}
	}
	return
}
