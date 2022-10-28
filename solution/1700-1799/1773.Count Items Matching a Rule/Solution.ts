function countMatches(
    items: string[][],
    ruleKey: string,
    ruleValue: string,
): number {
    const key = ruleKey === 'type' ? 0 : ruleKey === 'color' ? 1 : 2;
    return items.reduce((r, v) => r + (v[key] === ruleValue ? 1 : 0), 0);
}
