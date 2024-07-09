/**
 * class ArrayReader {
 *		// This is the ArrayReader's API interface.
 *		// You should not implement it, or speculate about its implementation
 *		get(index: number): number {};
 *  };
 */

function search(reader: ArrayReader, target: number): number {
    let r = 1;
    while (reader.get(r) < target) {
        r <<= 1;
    }
    let l = r >> 1;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (reader.get(mid) >= target) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return reader.get(l) === target ? l : -1;
}
