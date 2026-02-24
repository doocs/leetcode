function sortByBits(arr: number[]): number[] {
    const n = arr.length;

    for (let i = 0; i < n; ++i) {
        arr[i] += bitCount(arr[i]) * 100000;
    }

    arr.sort((a, b) => a - b);

    for (let i = 0; i < n; ++i) {
        arr[i] %= 100000;
    }

    return arr;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
