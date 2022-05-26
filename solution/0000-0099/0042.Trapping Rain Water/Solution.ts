function trap(height: number[]): number {
    let ans = 0;
    let left = 0,
        right = height.length - 1;
    let maxLeft = 0,
        maxRight = 0;
    while (left < right) {
        if (height[left] < height[right]) {
            // move left
            if (height[left] >= maxLeft) {
                maxLeft = height[left];
            } else {
                ans += maxLeft - height[left];
            }
            ++left;
        } else {
            // move right
            if (height[right] >= maxRight) {
                maxRight = height[right];
            } else {
                ans += maxRight - height[right];
            }
            --right;
        }
    }
    return ans;
}
