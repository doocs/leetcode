function maxConsecutiveAnswers(answerKey: string, k: number): number {
    const n = answerKey.length;
    const getMaxCount = (target: 'T' | 'F'): number => {
        let l = 0;
        let u = k;
        for (const c of answerKey) {
            if (c !== target) {
                u--;
            }
            if (u < 0 && answerKey[l++] !== target) {
                u++;
            }
        }
        return n - l;
    };
    return Math.max(getMaxCount('T'), getMaxCount('F'));
}
