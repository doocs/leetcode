func minArray(numbers []int) int {
	l, r := 0, len(numbers)-1
	for l < r {
		if numbers[l] < numbers[r] {
			break
		}
		mid := (l + r) >> 1
		if numbers[mid] > numbers[l] {
			l = mid + 1
		} else if numbers[mid] < numbers[l] {
			r = mid
		} else {
			l++
		}
	}
	return numbers[l]
}