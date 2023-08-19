func twoSum(numbers []int, target int) []int {
	for i, j := 0, len(numbers)-1; ; {
		x := numbers[i] + numbers[j]
		if x == target {
			return []int{i, j}
		}
		if x < target {
			i++
		} else {
			j--
		}
	}
}