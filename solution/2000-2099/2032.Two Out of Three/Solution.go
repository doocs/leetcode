func twoOutOfThree(nums1 []int, nums2 []int, nums3 []int) []int {
	s1, s2, s3 := get(nums1), get(nums2), get(nums3)
	var ans []int
	for i := 1; i <= 100; i++ {
		a, b, c := 0, 0, 0
		if s1[i] {
			a++
		}
		if s2[i] {
			b++
		}
		if s3[i] {
			c++
		}
		if a+b+c > 1 {
			ans = append(ans, i)
		}
	}
	return ans
}

func get(nums []int) map[int]bool {
	s := make(map[int]bool, 101)
	for _, num := range nums {
		s[num] = true
	}
	return s
}