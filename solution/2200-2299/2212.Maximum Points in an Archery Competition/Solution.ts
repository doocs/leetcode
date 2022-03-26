function maximumBobPoints(numArrows: number, aliceArrows: number[]): number[] {
    const dfs = (arr: number[], i: number, c: number): number[] => {
        if (i < 0 || c === 0) {
            arr[0] += c;
            return arr;
        }
        const a1 = dfs([...arr], i - 1, c);
        if (c > aliceArrows[i]) {
            arr[i] = aliceArrows[i] + 1;
            const a2 = dfs(arr, i - 1, c - aliceArrows[i] - 1);
            if (
                a2.reduce((p, v, i) => p + (v > 0 ? i : 0), 0) >=
                a1.reduce((p, v, i) => p + (v > 0 ? i : 0), 0)
            ) {
                return a2;
            }
        }
        return a1;
    };
    return dfs(new Array(12).fill(0), 11, numArrows);
}
