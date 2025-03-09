func findMajorityElement(nums []int) int {
	// Create a hashmap to store the frequency of each element
	frequency := make(map[int]int)

	// Traverse the array and count occurrences of each element
	for _, num := range nums {
		frequency[num]++
	}

	// Find the element with the maximum frequency
	maxCount := 0
	var majorityElement int
	for num, count := range frequency {
		if count > maxCount {
			maxCount = count
			majorityElement = num
		}
	}

	// Return the element with the maximum frequency (not checking n/2 times explicitly)
	return majorityElement
}