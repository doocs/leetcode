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
    const stack: NestedInteger[] = [];
    let num = 0;
    let negative = false;
    for (let i = 0; i < s.length; ++i) {
        const c = s[i];
        if (c === '-') {
            negative = true;
        } else if (c >= '0' && c <= '9') {
            num = num * 10 + c.charCodeAt(0) - '0'.charCodeAt(0);
        } else if (c === '[') {
            stack.push(new NestedInteger());
        } else if (c === ',' || c === ']') {
            if (s[i - 1] >= '0' && s[i - 1] <= '9') {
                stack[stack.length - 1].add(new NestedInteger(negative ? -num : num));
            }
            num = 0;
            negative = false;
            if (c === ']' && stack.length > 1) {
                const child = stack.pop()!;
                stack[stack.length - 1].add(child);
            }
        }
    }
    return stack[0];
}
