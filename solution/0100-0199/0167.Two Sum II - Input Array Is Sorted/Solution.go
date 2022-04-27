func twoSum(numbers []int, target int) []int {
	i, j := 1, len(numbers)
	for i < j {
		x := numbers[i-1] + numbers[j-1]
		if x == target {
			return []int{i, j}
		}
		if x < target {
			i++
		} else {
			j--
		}
	}
	return []int{-1, -1}
}