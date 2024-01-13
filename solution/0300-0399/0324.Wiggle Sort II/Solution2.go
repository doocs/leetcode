func wiggleSort(nums []int) {
	bucket := make([]int, 5001)
	for _, v := range nums {
		bucket[v]++
	}
	n, j := len(nums), 5000
	for i := 1; i < n; i += 2 {
		for bucket[j] == 0 {
			j--
		}
		nums[i] = j
		bucket[j]--
	}
	for i := 0; i < n; i += 2 {
		for bucket[j] == 0 {
			j--
		}
		nums[i] = j
		bucket[j]--
	}
}