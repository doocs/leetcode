function maxArea(height: number[]): number {
    const n = height.length;
    let res = 0;
    for (let i = 0; i < n - 1; i++) {
        for (let j = n - 1; j >= 0; j--) {
            if (height[i] * (j - i) < res) {
                break;
            }
            res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
        }
    }
    return res;
}
