function countSpecialIntegers(nums: number[]): number {
    const g = new Map<number, number[]>();

    for (let i = 0; i < nums.length; i++) {
        if (!g.has(nums[i])) {
            g.set(nums[i], []);
        }
        g.get(nums[i])!.push(i);
    }

    let ans = 0;
    for (const pos of g.values()) {
        if (pos.length < 3) {
            continue;
        }

        const d = pos[1] - pos[0];
        let ok = true;
        for (let i = 1; i < pos.length; i++) {
            if (pos[i] - pos[i - 1] !== d) {
                ok = false;
                break;
            }
        }

        if (ok) {
            ans++;
        }
    }

    return ans;
}
