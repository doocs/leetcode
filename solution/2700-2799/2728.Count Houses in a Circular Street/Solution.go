/**
 * Definition for a street.
 * type Street interface {
 *     OpenDoor()
 *     CloseDoor()
 *     IsDoorOpen() bool
 *     MoveRight()
 *     MoveLeft()
 * }
 */
func houseCount(street Street, k int) (ans int) {
	for ; k > 0; k-- {
		street.OpenDoor()
		street.MoveLeft()
	}
	for ; street.IsDoorOpen(); street.MoveLeft() {
		ans++
		street.CloseDoor()
	}
	return
}