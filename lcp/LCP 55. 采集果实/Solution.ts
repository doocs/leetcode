function getMinimumTime(time: number[], fruits: number[][], limit: number): number {
    let ans = 0;
    for (const [i, num] of fruits) {
        ans += Math.ceil(num / limit) * time[i];
    }
    return ans;
}
