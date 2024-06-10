function heightChecker(heights: number[]): number {
    const expected = [...heights].sort((a, b) => a - b);
    let ans = 0;

    for (let i = 0; i < heights.length; i++) {
        if (expected[i] !== heights[i]) ans++;
    }

    return ans;
}
