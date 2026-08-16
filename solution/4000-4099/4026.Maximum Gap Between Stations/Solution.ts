function maximumGap(skill: string, station: string): number {
    const n = skill.length;
    const m = station.length;

    const suf: number[] = Array(n).fill(0);
    let j = m - 1;

    for (let i = n - 1; i > 0; i--) {
        while (station[j] !== skill[i]) {
            j--;
        }

        suf[i] = j;
        j--;
    }

    let ans = 0;
    let pre = 0;

    for (let i = 0; i < n - 1; i++) {
        while (station[pre] !== skill[i]) {
            pre++;
        }

        ans = Math.max(ans, suf[i + 1] - pre);
        pre++;
    }

    return ans;
}
