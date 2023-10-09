function divingBoard(shorter: number, longer: number, k: number): number[] {
    if (k === 0) {
        return [];
    }
    if (longer === shorter) {
        return [longer * k];
    }
    const ans: number[] = [k + 1];
    for (let i = 0; i <= k; ++i) {
        ans[i] = longer * i + shorter * (k - i);
    }
    return ans;
}
