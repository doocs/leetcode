function maxConsecutiveAnswers(answerKey: string, k: number): number {
    const n = answerKey.length;
    const f = (c: string): number => {
        let [l, cnt] = [0, 0];
        for (const ch of answerKey) {
            cnt += ch === c ? 1 : 0;
            if (cnt > k) {
                cnt -= answerKey[l++] === c ? 1 : 0;
            }
        }
        return n - l;
    };
    return Math.max(f('T'), f('F'));
}
