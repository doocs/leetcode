function maxArea(height: number[]): number {
    let i = 0;
    let j = height.length - 1;
    let ans = 0;
    while (i < j) {
        const t = Math.min(height[i], height[j]) * (j - i);
        ans = Math.max(ans, t);
        if (height[i] < height[j]) {
            ++i;
        } else {
            --j;
        }
    }
    return ans;
}
