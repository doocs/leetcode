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
    if (s === '' || s === '[]') {
        return new NestedInteger();
    }
    if (s[0] !== '[') {
        return new NestedInteger(+s);
    }
    const ans: NestedInteger = new NestedInteger();
    let depth = 0;
    for (let i = 1, j = 1; i < s.length; ++i) {
        if (depth === 0 && (s[i] === ',' || i === s.length - 1)) {
            ans.add(deserialize(s.slice(j, i)));
            j = i + 1;
        } else if (s[i] === '[') {
            ++depth;
        } else if (s[i] === ']') {
            --depth;
        }
    }
    return ans;
}
