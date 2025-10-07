function successfulPairs(spells: number[], potions: number[], success: number): number[] {
    potions.sort((a, b) => a - b);
    const m = potions.length;
    const ans: number[] = [];

    for (const v of spells) {
        const targetPotion = success / v;
        const idx = _.sortedIndexBy(potions, targetPotion, p => p);
        ans.push(m - idx);
    }

    return ans;
}
