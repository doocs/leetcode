func twoSum(numbers []int, target int) []int {
	for i, j := 0, len(numbers)-1; ; {
		x := numbers[i] + numbers[j]
		if x == target {
			return []int{i + 1, j + 1}
		}
		if x < target {
			i++
		} else {
			j--
		}
	}
}