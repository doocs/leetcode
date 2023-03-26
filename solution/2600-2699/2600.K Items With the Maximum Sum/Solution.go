func kItemsWithMaximumSum(numOnes int, numZeros int, numNegOnes int, k int) int {
	if numOnes >= k {
		return k
	}
	k -= numOnes
	if numZeros >= k {
		return numOnes
	}
	k -= numZeros
	return numOnes - k
}