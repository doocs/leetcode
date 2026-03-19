function readBinaryWatch(turnedOn: number): string[] {
    const ans: string[] = [];

    for (let i = 0; i < 12; ++i) {
        for (let j = 0; j < 60; ++j) {
            if (bitCount(i) + bitCount(j) === turnedOn) {
                ans.push(`${i}:${j.toString().padStart(2, '0')}`);
            }
        }
    }

    return ans;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
