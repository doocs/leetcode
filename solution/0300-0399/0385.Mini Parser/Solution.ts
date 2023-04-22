/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *     If value is provided, then it holds a single integer
 *     Otherwise it holds an empty nested list
 *     constructor(value?: number) {
 *         ...
 *     };
 *
 *     Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     isInteger(): boolean {
 *         ...
 *     };
 *
 *     Return the single integer that this NestedInteger holds, if it holds a single integer
 *     Return null if this NestedInteger holds a nested list
 *     getInteger(): number | null {
 *         ...
 *     };
 *
 *     Set this NestedInteger to hold a single integer equal to value.
 *     setInteger(value: number) {
 *         ...
 *     };
 *
 *     Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
 *     add(elem: NestedInteger) {
 *         ...
 *     };
 *
 *     Return the nested list that this NestedInteger holds,
 *     or an empty list if this NestedInteger holds a single integer
 *     getList(): NestedInteger[] {
 *         ...
 *     };
 * };
 */

function deserialize(s: string): NestedInteger {
    if (s[0] !== '[') {
        return new NestedInteger(+s);
    }
    const stk: NestedInteger[] = [];
    let x = 0;
    let neg = false;
    for (let i = 0; i < s.length; ++i) {
        if (s[i] === '-') {
            neg = true;
        } else if (s[i] === '[') {
            stk.push(new NestedInteger());
        } else if (s[i] >= '0' && s[i] <= '9') {
            x = x * 10 + s[i].charCodeAt(0) - '0'.charCodeAt(0);
        } else if (s[i] === ',' || s[i] === ']') {
            if (s[i - 1] >= '0' && s[i - 1] <= '9') {
                stk[stk.length - 1].add(new NestedInteger(neg ? -x : x));
            }
            x = 0;
            neg = false;
            if (s[i] === ']' && stk.length > 1) {
                const t = stk.pop()!;
                stk[stk.length - 1].add(t);
            }
        }
    }
    return stk[0];
}
