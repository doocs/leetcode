/**
 * Definition for a street.
 * class Street {
 *     public Street(int[] doors);
 *     public void openDoor();
 *     public void closeDoor();
 *     public boolean isDoorOpen();
 *     public void moveRight();
 *     public void moveLeft();
 * }
 */
class Solution {
    public int houseCount(Street street, int k) {
        while (k-- > 0) {
            street.openDoor();
            street.moveLeft();
        }
        int ans = 0;
        while (street.isDoorOpen()) {
            ++ans;
            street.closeDoor();
            street.moveLeft();
        }
        return ans;
    }
}