function maxConsecutiveAnswers(answerKey: string, k: number): number {
    const n = answerKey.length;
    const f = (c: string): number => {
        let [ans, cnt, j] = [0, 0, 0];
        for (let i = 0; i < n; ++i) {
            cnt += answerKey[i] === c ? 0 : 1;
            while (cnt > k) {
                cnt -= answerKey[j++] === c ? 0 : 1;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    };
    return Math.max(f('T'), f('F'));
}
