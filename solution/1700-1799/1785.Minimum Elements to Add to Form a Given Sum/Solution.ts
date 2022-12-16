function minElements(nums: number[], limit: number, goal: number): number {
    const sum = nums.reduce((r, v) => r + v, 0);
    const diff = Math.abs(goal - sum);
    return Math.floor((diff + limit - 1) / limit);
}
