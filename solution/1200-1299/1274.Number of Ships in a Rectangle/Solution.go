/**
 * // This is Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * type Sea struct {
 *     func hasShips(topRight, bottomLeft []int) bool {}
 * }
 */

func countShips(sea Sea, topRight, bottomLeft []int) int {
	x1, y1 := bottomLeft[0], bottomLeft[1]
	x2, y2 := topRight[0], topRight[1]
	if x1 > x2 || y1 > y2 {
		return 0
	}
	if !sea.hasShips(topRight, bottomLeft) {
		return 0
	}
	if x1 == x2 && y1 == y2 {
		return 1
	}
	midx := (x1 + x2) >> 1
	midy := (y1 + y2) >> 1
	a := countShips(sea, topRight, []int{midx + 1, midy + 1})
	b := countShips(sea, []int{midx, y2}, []int{x1, midy + 1})
	c := countShips(sea, []int{midx, midy}, bottomLeft)
	d := countShips(sea, []int{x2, midy}, []int{midx + 1, y1})
	return a + b + c + d
}