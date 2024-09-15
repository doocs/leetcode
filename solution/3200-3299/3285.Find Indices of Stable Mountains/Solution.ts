function stableMountains(height: number[], threshold: number): number[] {
    const ans: number[] = [];
    for (let i = 1; i < height.length; ++i) {
        if (height[i - 1] > threshold) {
            ans.push(i);
        }
    }
    return ans;
}
