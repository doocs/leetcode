function countTime(time: string): number {
    let ans = 0;
    for (let h = 0; h < 24; ++h) {
        for (let m = 0; m < 60; ++m) {
            const s = `${h}`.padStart(2, '0') + ':' + `${m}`.padStart(2, '0');
            let ok = 1;
            for (let i = 0; i < 5; ++i) {
                if (s[i] !== time[i] && time[i] !== '?') {
                    ok = 0;
                    break;
                }
            }
            ans += ok;
        }
    }
    return ans;
}
