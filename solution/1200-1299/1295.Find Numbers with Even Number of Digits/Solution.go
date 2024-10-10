func findNumbers(nums []int) (ans int) {
	for _, x := range nums {
		if len(strconv.Itoa(x))%2 == 0 {
			ans++
		}
	}
	return
}
