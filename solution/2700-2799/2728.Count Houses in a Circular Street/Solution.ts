/**
 * Definition for a street.
 * class Street {
 *     constructor(doors: number[]);
 *     public openDoor(): void;
 *     public closeDoor(): void;
 *     public isDoorOpen(): boolean;
 *     public moveRight(): void;
 *     public moveLeft(): void;
 * }
 */
function houseCount(street: Street | null, k: number): number {
    while (k-- > 0) {
        street.openDoor();
        street.moveLeft();
    }
    let ans = 0;
    while (street.isDoorOpen()) {
        ++ans;
        street.closeDoor();
        street.moveLeft();
    }
    return ans;
}
