func findEvenNumbers(digits []int) []int {
	counter := count(digits)
	var ans []int
	for i := 100; i < 1000; i += 2 {
		t := make([]int, 3)
		k := i
		for j := 0; k > 0; j++ {
			t[j] = k % 10
			k /= 10
		}
		cnt := count(t)
		if check(counter, cnt) {
			ans = append(ans, i)
		}
	}
	return ans
}

func count(nums []int) []int {
	counter := make([]int, 10)
	for _, num := range nums {
		counter[num]++
	}
	return counter
}

func check(cnt1, cnt2 []int) bool {
	for i := 0; i < 10; i++ {
		if cnt1[i] < cnt2[i] {
			return false
		}
	}
	return true
}