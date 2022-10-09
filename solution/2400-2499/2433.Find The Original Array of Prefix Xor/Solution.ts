function findArray(pref: number[]): number[] {
    let ans = pref.slice();
    for (let i = 1; i < pref.length; i++) {
        ans[i] = pref[i - 1] ^ pref[i];
    }
    return ans;
}
