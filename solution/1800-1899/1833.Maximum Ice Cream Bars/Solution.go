func maxIceCream(costs []int, coins int) int {
	sort.Ints(costs)
	n := len(costs)
	ans := 0
	for i := 0; i < n && coins >= costs[i]; i++ {
		ans++
		coins -= costs[i]
	}
	return ans
}
