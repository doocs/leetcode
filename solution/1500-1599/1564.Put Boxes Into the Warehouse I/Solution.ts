function maxBoxesInWarehouse(boxes: number[], warehouse: number[]): number {
    const n = warehouse.length;
    const left: number[] = new Array(n);
    left[0] = warehouse[0];
    for (let i = 1; i < n; ++i) {
        left[i] = Math.min(left[i - 1], warehouse[i]);
    }
    boxes.sort((a, b) => a - b);
    let i = 0;
    let j = n - 1;
    while (i < boxes.length) {
        while (j >= 0 && left[j] < boxes[i]) {
            --j;
        }
        if (j < 0) {
            break;
        }
        ++i;
        --j;
    }
    return i;
}
