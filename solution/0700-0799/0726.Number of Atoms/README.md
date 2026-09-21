---
comments: true
difficulty: 困难
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

<!-- thinking:start -->

> **思考**
>
> 化学式含括号与下标，需要统计每种原子的个数并按名字排序输出。公式长度 $\le 1000$，递归下降或栈都可以处理嵌套。
>
> 括号的倍数作用在整个子式上，从右往左扫描时，先遇到的下标可以立刻乘进当前倍数：遇 `)` 把当前倍数压栈并乘上下标，遇 `(` 再弹出恢复。
>
> 按此逆向累加：数字拼成 $\textit{freq}$，元素名（含后续小写）计入 $\textit{freq}\times\textit{multiplier}$。最后对键排序，个数为 $1$ 时省略数字。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOfAtoms(self, formula: str) -> str:
        cnt = defaultdict(int)
        stack = []
        multiplier, freq = 1, 0
        i = len(formula) - 1
        while i >= 0:
            c = formula[i]
            if c.islower():
                end = i
                i -= 1
                while i >= 0 and formula[i].islower():
                    i -= 1
                cnt[formula[i : end + 1]] += max(freq, 1) * multiplier
                freq = 0
            elif c.isupper():
                cnt[c] += max(freq, 1) * multiplier
                freq = 0
            elif c.isdigit():
                freq = ord(c) - 48
                p = 10
                while i - 1 >= 0 and formula[i - 1].isdigit():
                    i -= 1
                    freq += p * (ord(formula[i]) - 48)
                    p *= 10
            elif c == ')':
                stack.append(multiplier)
                multiplier *= max(freq, 1)
                freq = 0
            else:
                multiplier = stack.pop()
            i -= 1
        ans = []
        for key in sorted(cnt):
            ans.append(key)
            if cnt[key] > 1:
                ans.append(str(cnt[key]))
        return ''.join(ans)
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
class Solution {
public:
    string countOfAtoms(string formula) {
        unordered_map<string, int> cnt;
        vector<int> stk;
        int multiplier = 1, freq = 0;
        for (int i = formula.size() - 1; i >= 0; --i) {
            if (formula[i] >= 'a' && formula[i] <= 'z') {
                int end = i--;
                while (i >= 0 && formula[i] >= 'a' && formula[i] <= 'z') {
                    --i;
                }
                cnt[formula.substr(i, end - i + 1)] += max(freq, 1) * multiplier;
                freq = 0;
            } else if (formula[i] >= 'A' && formula[i] <= 'Z') {
                cnt[string(1, formula[i])] += max(freq, 1) * multiplier;
                freq = 0;
            } else if (formula[i] >= '0' && formula[i] <= '9') {
                freq = formula[i] - '0';
                int p = 10;
                while (i - 1 >= 0 && formula[i - 1] >= '0' && formula[i - 1] <= '9') {
                    freq += p * (formula[--i] - '0');
                    p *= 10;
                }
            } else if (formula[i] == ')') {
                stk.push_back(multiplier);
                multiplier *= max(freq, 1);
                freq = 0;
            } else {
                multiplier = stk.back();
                stk.pop_back();
            }
        }
        vector<string> keys;
        for (auto& [k, _] : cnt) {
            keys.push_back(k);
        }
        sort(keys.begin(), keys.end());
        string ans;
        for (auto& key : keys) {
            ans += key;
            if (cnt[key] > 1) {
                ans += to_string(cnt[key]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countOfAtoms(formula string) string {
	cnt := map[string]int{}
	var stack []int
	multiplier, freq := 1, 0
	for i := len(formula) - 1; i >= 0; i-- {
		c := formula[i]
		if c >= 'a' && c <= 'z' {
			end := i
			i--
			for i >= 0 && formula[i] >= 'a' && formula[i] <= 'z' {
				i--
			}
			cnt[formula[i:end+1]] += max(freq, 1) * multiplier
			freq = 0
		} else if c >= 'A' && c <= 'Z' {
			cnt[formula[i:i+1]] += max(freq, 1) * multiplier
			freq = 0
		} else if c >= '0' && c <= '9' {
			freq = int(c - '0')
			p := 10
			for i-1 >= 0 && formula[i-1] >= '0' && formula[i-1] <= '9' {
				i--
				freq += p * int(formula[i]-'0')
				p *= 10
			}
		} else if c == ')' {
			stack = append(stack, multiplier)
			multiplier *= max(freq, 1)
			freq = 0
		} else {
			multiplier = stack[len(stack)-1]
			stack = stack[:len(stack)-1]
		}
	}
	keys := make([]string, 0, len(cnt))
	for k := range cnt {
		keys = append(keys, k)
	}
	sort.Strings(keys)
	ans := []byte{}
	for _, key := range keys {
		ans = append(ans, key...)
		if cnt[key] > 1 {
			ans = append(ans, strconv.Itoa(cnt[key])...)
		}
	}
	return string(ans)
}
```

#### TypeScript

```ts
function countOfAtoms(formula: string): string {
    const cnt = new Map<string, number>();
    const stack: number[] = [];
    let multiplier = 1;
    let freq = 0;
    for (let i = formula.length - 1; i >= 0; --i) {
        const ch = formula.charCodeAt(i);
        if (ch >= 97 && ch <= 122) {
            const end = i--;
            while (i >= 0 && formula.charCodeAt(i) >= 97 && formula.charCodeAt(i) <= 122) {
                --i;
            }
            const key = formula.slice(i, end + 1);
            cnt.set(key, (cnt.get(key) ?? 0) + Math.max(freq, 1) * multiplier);
            freq = 0;
        } else if (ch >= 65 && ch <= 90) {
            const key = formula[i];
            cnt.set(key, (cnt.get(key) ?? 0) + Math.max(freq, 1) * multiplier);
            freq = 0;
        } else if (ch >= 48 && ch <= 57) {
            freq = ch - 48;
            let p = 10;
            while (
                i - 1 >= 0 &&
                formula.charCodeAt(i - 1) >= 48 &&
                formula.charCodeAt(i - 1) <= 57
            ) {
                freq += p * (formula.charCodeAt(--i) - 48);
                p *= 10;
            }
        } else if (formula[i] === ')') {
            stack.push(multiplier);
            multiplier *= Math.max(freq, 1);
            freq = 0;
        } else {
            multiplier = stack.pop()!;
        }
    }
    return [...cnt.entries()]
        .sort(([a], [b]) => a.localeCompare(b))
        .map(([k, v]) => (v > 1 ? k + v : k))
        .join('');
}
```

#### JavaScript

```js
/**
 * @param {string} formula
 * @return {string}
 */
var countOfAtoms = function (formula) {
    const cnt = new Map();
    const stack = [];
    let multiplier = 1;
    let freq = 0;
    for (let i = formula.length - 1; i >= 0; --i) {
        const ch = formula.charCodeAt(i);
        if (ch >= 97 && ch <= 122) {
            const end = i--;
            while (i >= 0 && formula.charCodeAt(i) >= 97 && formula.charCodeAt(i) <= 122) {
                --i;
            }
            const key = formula.slice(i, end + 1);
            cnt.set(key, (cnt.get(key) ?? 0) + Math.max(freq, 1) * multiplier);
            freq = 0;
        } else if (ch >= 65 && ch <= 90) {
            const key = formula[i];
            cnt.set(key, (cnt.get(key) ?? 0) + Math.max(freq, 1) * multiplier);
            freq = 0;
        } else if (ch >= 48 && ch <= 57) {
            freq = ch - 48;
            let p = 10;
            while (
                i - 1 >= 0 &&
                formula.charCodeAt(i - 1) >= 48 &&
                formula.charCodeAt(i - 1) <= 57
            ) {
                freq += p * (formula.charCodeAt(--i) - 48);
                p *= 10;
            }
        } else if (formula[i] === ')') {
            stack.push(multiplier);
            multiplier *= Math.max(freq, 1);
            freq = 0;
        } else {
            multiplier = stack.pop();
        }
    }
    return [...cnt.entries()]
        .sort(([a], [b]) => a.localeCompare(b))
        .map(([k, v]) => (v > 1 ? k + v : k))
        .join('');
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
