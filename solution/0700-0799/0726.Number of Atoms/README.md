---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0726.Number%20of%20Atoms/README.md
tags:
    - 栈
    - 哈希表
    - 字符串
    - 排序
---

<!-- problem:start -->

# [726. 原子的数量](https://leetcode.cn/problems/number-of-atoms)

[English Version](/solution/0700-0799/0726.Number%20of%20Atoms/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串化学式 <code>formula</code> ，返回 <strong>每种原子的数量</strong> 。</p>

<p>原子总是以一个大写字母开始，接着跟随 0 个或任意个小写字母，表示原子的名字。</p>

<p>如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。</p>

<ul>
	<li>例如，<code>"H2O"</code> 和 <code>"H2O2"</code> 是可行的，但 <code>"H1O2"</code> 这个表达是不可行的。</li>
</ul>

<p>两个化学式连在一起可以构成新的化学式。</p>

<ul>
	<li>例如 <code>"H2O2He3Mg4"</code> 也是化学式。</li>
</ul>

<p>由括号括起的化学式并佐以数字（可选择性添加）也是化学式。</p>

<ul>
	<li>例如 <code>"(H2O2)"</code> 和 <code>"(H2O2)3"</code> 是化学式。</li>
</ul>

<p>返回所有原子的数量，格式为：第一个（按字典序）原子的名字，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>formula = "H2O"
<strong>输出：</strong>"H2O"
<strong>解释：</strong>原子的数量是 {'H': 2, 'O': 1}。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>formula = "Mg(OH)2"
<strong>输出：</strong>"H2MgO2"
<strong>解释：</strong>原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>formula = "K4(ON(SO3)2)2"
<strong>输出：</strong>"K4N2O14S4"
<strong>解释：</strong>原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= formula.length&nbsp;&lt;= 1000</code></li>
	<li><code>formula</code> 由英文字母、数字、<code>'('</code> 和 <code>')'</code> 组成</li>
	<li><code>formula</code> 总是有效的化学式</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java
class Solution {
    public String countOfAtoms(String formula) {
        Map<String, Integer> map = new HashMap<>();
        int[] stack = new int[1000];
        int top = 0, multiplier = 1, freq = 0;
        char[] c = formula.toCharArray();
        for (int i = c.length - 1; i >= 0; i--) {
            if (c[i] >= 'a' && c[i] <= 'z') {
                int end = i--;
                while (i >= 0 && c[i] >= 'a' && c[i] <= 'z') i--;
                String key = new String(c, i, end - i + 1);
                map.put(key, map.getOrDefault(key, 0) + Math.max(freq, 1) * multiplier);
                freq = 0;
            } else if (c[i] >= 'A' && c[i] <= 'Z') {
                String key = new String(c, i, 1);
                map.put(key, map.getOrDefault(key, 0) + Math.max(freq, 1) * multiplier);
                freq = 0;
            } else if (c[i] >= '0' && c[i] <= '9') {
                freq = c[i] - '0';
                int p = 10;
                while (i - 1 >= 0 && c[i - 1] >= '0' && c[i - 1] <= '9') {
                    freq += p * (c[--i] - '0');
                    p *= 10;
                }
            } else if (c[i] == ')') {
                stack[top++] = multiplier;
                multiplier *= Math.max(freq, 1);
                freq = 0;
            } else {
                multiplier = stack[--top];
            }
        }
        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key);
            int f = map.get(key);
            if (f > 1) sb.append(f);
        }
        return sb.toString();
    }
}
```

#### C++

```cpp

```

#### Go

```go

```

#### TypeScript

```ts
function countOfAtoms(formula: string): string {
    const getCount = (formula: string, factor = 1) => {
        const n = formula.length;
        const cnt: Record<string, number> = {};
        const s: string[] = [];
        let [atom, c] = ['', 0];

        for (let i = 0; i <= n; i++) {
            if (formula[i] === '(') {
                const stk: string[] = ['('];
                let j = i;
                while (stk.length) {
                    j++;
                    if (formula[j] === '(') stk.push('(');
                    else if (formula[j] === ')') stk.pop();
                }

                const molecule = formula.slice(i + 1, j);
                const nextFactor: string[] = [];

                while (isDigit(formula[++j])) {
                    nextFactor.push(formula[j]);
                }

                const nextC = getCount(molecule, +nextFactor.join('') || 1);
                for (const [atom, c] of Object.entries(nextC)) {
                    cnt[atom] = (cnt[atom] ?? 0) + c * factor;
                }

                i = j - 1;
                continue;
            }

            if (s.length && (!formula[i] || isUpper(formula[i]))) {
                [atom, c] = getAtom(s);

                c *= factor;
                cnt[atom] = (cnt[atom] ?? 0) + c;
                s.length = 0;
            }

            s.push(formula[i]);
        }

        return cnt;
    };

    return Object.entries(getCount(formula))
        .sort(([a], [b]) => a.localeCompare(b))
        .map(([a, b]) => (b > 1 ? a + b : a))
        .join('');
}

const regex = {
    atom: /(\D+)(\d+)?/,
    isUpper: /[A-Z]+/,
};
const getAtom = (s: string[]): [string, number] => {
    const [_, atom, c] = regex.atom.exec(s.join(''))!;
    return [atom, c ? +c : 1];
};
const isDigit = (ch: string) => !Number.isNaN(Number.parseInt(ch));
const isUpper = (ch: string) => regex.isUpper.test(ch);
```

#### JavaScript

```js
/**
 * @param {string} formula
 * @return {string}
 */
var countOfAtoms = function (formula) {
    const getCount = (formula, factor = 1) => {
        const n = formula.length;
        const cnt = {};
        const s = [];
        let [atom, c] = ['', 0];

        for (let i = 0; i <= n; i++) {
            if (formula[i] === '(') {
                const stk = ['('];
                let j = i;
                while (stk.length) {
                    j++;
                    if (formula[j] === '(') stk.push('(');
                    else if (formula[j] === ')') stk.pop();
                }

                const molecule = formula.slice(i + 1, j);
                const nextFactor = [];

                while (isDigit(formula[++j])) {
                    nextFactor.push(formula[j]);
                }

                const nextC = getCount(molecule, +nextFactor.join('') || 1);
                for (const [atom, c] of Object.entries(nextC)) {
                    cnt[atom] = (cnt[atom] ?? 0) + c * factor;
                }

                i = j - 1;
                continue;
            }

            if (s.length && (!formula[i] || isUpper(formula[i]))) {
                [atom, c] = getAtom(s);

                c *= factor;
                cnt[atom] = (cnt[atom] ?? 0) + c;
                s.length = 0;
            }

            s.push(formula[i]);
        }

        return cnt;
    };

    return Object.entries(getCount(formula))
        .sort(([a], [b]) => a.localeCompare(b))
        .map(([a, b]) => (b > 1 ? a + b : a))
        .join('');
};

const regex = {
    atom: /(\D+)(\d+)?/,
    isUpper: /[A-Z]+/,
};
const getAtom = s => {
    const [_, atom, c] = regex.atom.exec(s.join(''));
    return [atom, c ? +c : 1];
};
const isDigit = ch => !Number.isNaN(Number.parseInt(ch));
const isUpper = ch => regex.isUpper.test(ch);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
