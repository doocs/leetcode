func numWaterBottles(numBottles int, numExchange int) int {
	ans := numBottles
	for numBottles >= numExchange {
		numBottles -= (numExchange - 1)
		ans++
	}
	return ans
}