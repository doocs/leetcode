function maxCaloriesBurnt(heights: number[]): number {
    heights.sort((a, b) => a - b);
    let ans = 0;
    let pre = 0;
    let [l, r] = [0, heights.length - 1];
    while (l < r) {
        ans += (heights[r] - pre) ** 2;
        ans += (heights[l] - heights[r]) ** 2;
        pre = heights[l];
        l++;
        r--;
    }
    ans += (heights[r] - pre) ** 2;
    return ans;
}
