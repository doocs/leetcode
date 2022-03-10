function minSteps(s: string, t: string): number {
    let count1 = new Array(128).fill(0);
    let count2 = new Array(128).fill(0);
    for (let char of s) {
        count1[char.charCodeAt(0)]++;
    }
    for (let char of t) {
        count2[char.charCodeAt(0)]++;
    }
    let ans = 0;
    for (let i = 0; i < 128; i++) {
        ans += Math.abs(count1[i] - count2[i]);
    }
    return ans;
};