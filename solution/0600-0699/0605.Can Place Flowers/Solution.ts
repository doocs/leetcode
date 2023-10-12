function canPlaceFlowers(flowerbed: number[], n: number): boolean {
    const m = flowerbed.length;
    for (let i = 0; i < m; ++i) {
        const l = i === 0 ? 0 : flowerbed[i - 1];
        const r = i === m - 1 ? 0 : flowerbed[i + 1];
        if (l + flowerbed[i] + r === 0) {
            flowerbed[i] = 1;
            --n;
        }
    }
    return n <= 0;
}
