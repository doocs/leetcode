func findNumbers(nums []int) (ans int) {
	for _, v := range nums {
		if len(strconv.Itoa(v))%2 == 0 {
			ans++
		}
	}
	return
}