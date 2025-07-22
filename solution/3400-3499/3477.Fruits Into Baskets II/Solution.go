func numOfUnplacedFruits(fruits []int, baskets []int) int {
	n := len(fruits)
	ans := n
	vis := make([]bool, n)
	for _, x := range fruits {
		for i, y := range baskets {
			if y >= x && !vis[i] {
				vis[i] = true
				ans--
				break
			}
		}
	}
	return ans
}
