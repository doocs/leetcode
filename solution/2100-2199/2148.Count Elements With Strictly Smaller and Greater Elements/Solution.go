func countElements(nums []int) int {
	mi, mx := int(1e6), -int(1e6)
	for _, num := range nums {
		if num < mi {
			mi = num
		}
		if num > mx {
			mx = num
		}
	}
	ans := 0
	for _, num := range nums {
		if mi < num && num < mx {
			ans++
		}
	}
	return ans
}