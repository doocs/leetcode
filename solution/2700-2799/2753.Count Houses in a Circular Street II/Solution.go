/**
 * Definition for a street.
 * type Street interface {
 *     CloseDoor()
 *     IsDoorOpen() bool
 *     MoveRight()
 * }
 */
func houseCount(street Street, k int) (ans int) {
	for !street.IsDoorOpen() {
		street.MoveRight()
	}
	for i := 1; i <= k; i++ {
		street.MoveRight()
		if street.IsDoorOpen() {
			ans = i
			street.CloseDoor()
		}
	}
	return
}