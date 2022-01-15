func totalMoney(n int) int {
	a, b := n/7, n%7
	return (28+28+7*(a-1))*a/2 + (a*2+b+1)*b/2
}