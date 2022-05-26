function checkInclusion(s1: string, s2: string): boolean {
    // 滑动窗口方案
    if (s1.length > s2.length) {
        return false;
    }

    const n = s1.length;
    const m = s2.length;

    const toCode = (s: string) => s.charCodeAt(0) - 97;
    const isMatch = () => {
        for (let i = 0; i < 26; i++) {
            if (arr1[i] !== arr2[i]) {
                return false;
            }
        }
        return true;
    };

    const arr1 = new Array(26).fill(0);
    for (const s of s1) {
        const index = toCode(s);
        arr1[index]++;
    }

    const arr2 = new Array(26).fill(0);
    for (let i = 0; i < n; i++) {
        const index = toCode(s2[i]);
        arr2[index]++;
    }

    for (let l = 0, r = n; r < m; l++, r++) {
        if (isMatch()) {
            return true;
        }

        const i = toCode(s2[l]);
        const j = toCode(s2[r]);
        arr2[i]--;
        arr2[j]++;
    }
    return isMatch();
}
