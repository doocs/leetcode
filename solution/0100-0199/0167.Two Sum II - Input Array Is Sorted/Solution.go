func twoSum(numbers []int, target int) []int {
	for i, n := 0, len(numbers); ; i++ {
		x := target - numbers[i]
		j := sort.SearchInts(numbers[i+1:], x) + i + 1
		if j < n && numbers[j] == x {
			return []int{i + 1, j + 1}
		}
	}
}