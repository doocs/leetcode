function largestWordCount(messages: string[], senders: string[]): string {
    const cnt: { [key: string]: number } = {};

    for (let i = 0; i < messages.length; ++i) {
        const v = messages[i].split(' ').length;
        cnt[senders[i]] = (cnt[senders[i]] || 0) + v;
    }

    let ans = senders[0];
    for (const k in cnt) {
        if (cnt[ans] < cnt[k] || (cnt[ans] === cnt[k] && ans < k)) {
            ans = k;
        }
    }

    return ans;
}
