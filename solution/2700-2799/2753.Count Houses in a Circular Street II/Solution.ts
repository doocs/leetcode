/**
 * Definition for a street.
 * class Street {
 *     constructor(doors: number[]);
 *     public closeDoor(): void;
 *     public isDoorOpen(): boolean;
 *     public moveRight(): void;
 * }
 */
function houseCount(street: Street | null, k: number): number {
    while (!street.isDoorOpen()) {
        street.moveRight();
    }
    let ans = 0;
    for (let i = 1; i <= k; ++i) {
        street.moveRight();
        if (street.isDoorOpen()) {
            ans = i;
            street.closeDoor();
        }
    }
    return ans;
}
