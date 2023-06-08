/**
 * Definition for a street.
 * class Street {
 * public:
 *     Street(vector<int> doors);
 *     void openDoor();
 *     void closeDoor();
 *     bool isDoorOpen();
 *     void moveRight();
 *     void moveLeft();
 * };
 */
class Solution {
public:
    int houseCount(Street* street, int k) {
        while (k--) {
            street->openDoor();
            street->moveLeft();
        }
        int ans = 0;
        while (street->isDoorOpen()) {
            ans++;
            street->closeDoor();
            street->moveLeft();
        }
        return ans;
    }
};