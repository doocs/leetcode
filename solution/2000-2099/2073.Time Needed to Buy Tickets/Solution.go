func timeRequiredToBuy(tickets []int, k int) int {
	ans := 1
	for i := 0; ; i = (i + 1) % len(tickets) {
		if i == k && tickets[i] == 1 {
			return ans
		}
		if tickets[i] > 0 {
			tickets[i]--
			ans++
		}
	}
}