function maxNumberOfBalloons(text: string): number {
    let targets: Set<string> = new Set('balloon'.split(''));
    let cnt = new Array(126).fill(0);
    for (let char of text) {
        if (targets.has(char)) {
            cnt[char.charCodeAt(0)]++;
        }
    }
    cnt['l'.charCodeAt(0)] >>= 1;
    cnt['o'.charCodeAt(0)] >>= 1;
    let ans = Number.MAX_SAFE_INTEGER;
    for (let char of targets) {
        ans = Math.min(cnt[char.charCodeAt(0)], ans);
    }
    return ans;
}
