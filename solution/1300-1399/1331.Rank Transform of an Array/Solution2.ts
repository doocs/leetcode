function arrayRankTransform(arr: number[]): number[] {
    const sorted = [...new Set(arr)].sort((a, b) => a - b);
    const map = new Map<number, number>();
    let c = 1;

    for (const x of sorted) {
        map.set(x, c++);
    }

    return arr.map(x => map.get(x)!);
}
