function distributeCandies(candies: number, num_people: number): number[] {
    const ans: number[] = Array(num_people).fill(0);
    for (let i = 0; candies > 0; ++i) {
        ans[i % num_people] += Math.min(candies, i + 1);
        candies -= Math.min(candies, i + 1);
    }
    return ans;
}
