function canMakePalindromeQueries(s: string, queries: number[][]): boolean[] {
    const n: number = s.length;
    const m: number = n >> 1;
    const t: string = s.slice(m).split('').reverse().join('');
    s = s.slice(0, m);

    const pre1: number[][] = Array.from({ length: m + 1 }, () => Array(26).fill(0));
    const pre2: number[][] = Array.from({ length: m + 1 }, () => Array(26).fill(0));
    const diff: number[] = Array(m + 1).fill(0);
    for (let i = 1; i <= m; ++i) {
        pre1[i] = [...pre1[i - 1]];
        pre2[i] = [...pre2[i - 1]];
        ++pre1[i][s.charCodeAt(i - 1) - 'a'.charCodeAt(0)];
        ++pre2[i][t.charCodeAt(i - 1) - 'a'.charCodeAt(0)];
        diff[i] = diff[i - 1] + (s[i - 1] === t[i - 1] ? 0 : 1);
    }

    const ans: boolean[] = Array(queries.length).fill(false);
    for (let i = 0; i < queries.length; ++i) {
        const q: number[] = queries[i];
        const [a, b] = [q[0], q[1]];
        const [c, d] = [n - 1 - q[3], n - 1 - q[2]];
        ans[i] = a <= c ? check(pre1, pre2, diff, a, b, c, d) : check(pre2, pre1, diff, c, d, a, b);
    }
    return ans;
}

function check(
    pre1: number[][],
    pre2: number[][],
    diff: number[],
    a: number,
    b: number,
    c: number,
    d: number,
): boolean {
    if (diff[a] > 0 || diff[diff.length - 1] - diff[Math.max(b, d) + 1] > 0) {
        return false;
    }

    if (d <= b) {
        return arraysEqual(count(pre1, a, b), count(pre2, a, b));
    }

    if (b < c) {
        return (
            diff[c] - diff[b + 1] === 0 &&
            arraysEqual(count(pre1, a, b), count(pre2, a, b)) &&
            arraysEqual(count(pre1, c, d), count(pre2, c, d))
        );
    }

    const cnt1: number[] | null = sub(count(pre1, a, b), count(pre2, a, c - 1));
    const cnt2: number[] | null = sub(count(pre2, c, d), count(pre1, b + 1, d));

    return cnt1 !== null && cnt2 !== null && arraysEqual(cnt1, cnt2);
}

function count(pre: number[][], i: number, j: number): number[] {
    const cnt: number[] = new Array(26).fill(0);
    for (let k = 0; k < 26; ++k) {
        cnt[k] = pre[j + 1][k] - pre[i][k];
    }
    return cnt;
}

function sub(cnt1: number[], cnt2: number[]): number[] | null {
    const cnt: number[] = new Array(26).fill(0);
    for (let i = 0; i < 26; ++i) {
        cnt[i] = cnt1[i] - cnt2[i];
        if (cnt[i] < 0) {
            return null;
        }
    }
    return cnt;
}

function arraysEqual(arr1: number[], arr2: number[]): boolean {
    return JSON.stringify(arr1) === JSON.stringify(arr2);
}
