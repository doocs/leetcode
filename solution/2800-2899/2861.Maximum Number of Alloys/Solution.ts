function maxNumberOfAlloys(
    n: number,
    k: number,
    budget: number,
    composition: number[][],
    stock: number[],
    cost: number[],
): number {
    let l = 0;
    let r = budget + stock[0];
    const isValid = (target: number): boolean => {
        for (const currMachine of composition) {
            let remain = budget;
            for (let i = 0; i < n; ++i) {
                let need = Math.max(0, target * currMachine[i] - stock[i]);
                remain -= need * cost[i];
            }
            if (remain >= 0) {
                return true;
            }
        }
        return false;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (isValid(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
