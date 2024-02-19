function numRescueBoats(people: number[], limit: number): number {
    people.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0, j = people.length - 1; i <= j; --j) {
        if (people[i] + people[j] <= limit) {
            ++i;
        }
        ++ans;
    }
    return ans;
}
