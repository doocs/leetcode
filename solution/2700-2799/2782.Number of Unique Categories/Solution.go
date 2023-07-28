/**
 * Definition for a category handler.
 * type CategoryHandler interface {
 *  HaveSameCategory(int, int) bool
 * }
 */
func numberOfCategories(n int, categoryHandler CategoryHandler) (ans int) {
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for a := 0; a < n; a++ {
		for b := a + 1; b < n; b++ {
			if categoryHandler.HaveSameCategory(a, b) {
				p[find(a)] = find(b)
			}
		}
	}
	for i, x := range p {
		if i == x {
			ans++
		}
	}
	return
}