---
comments: true
difficulty: Hard
tags:
    - Stack
    - Hash Table
    - String
    - Sorting
---

<!-- problem:start -->

# [726. Number of Atoms](https://leetcode.com/problems/number-of-atoms)

[中文文档](/solution/0700-0799/0726.Number%20of%20Atoms/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>formula</code> representing a chemical formula, return <em>the count of each atom</em>.</p>

<p>The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.</p>

<p>One or more digits representing that element&#39;s count may follow if the count is greater than <code>1</code>. If the count is <code>1</code>, no digits will follow.</p>

<ul>
	<li>For example, <code>&quot;H2O&quot;</code> and <code>&quot;H2O2&quot;</code> are possible, but <code>&quot;H1O2&quot;</code> is impossible.</li>
</ul>

<p>Two formulas are concatenated together to produce another formula.</p>

<ul>
	<li>For example, <code>&quot;H2O2He3Mg4&quot;</code> is also a formula.</li>
</ul>

<p>A formula placed in parentheses, and a count (optionally added) is also a formula.</p>

<ul>
	<li>For example, <code>&quot;(H2O2)&quot;</code> and <code>&quot;(H2O2)3&quot;</code> are formulas.</li>
</ul>

<p>Return the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than <code>1</code>), followed by the second name (in sorted order), followed by its count (if that count is more than <code>1</code>), and so on.</p>

<p>The test cases are generated so that all the values in the output fit in a <strong>32-bit</strong> integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> formula = &quot;H2O&quot;
<strong>Output:</strong> &quot;H2O&quot;
<strong>Explanation:</strong> The count of elements are {&#39;H&#39;: 2, &#39;O&#39;: 1}.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> formula = &quot;Mg(OH)2&quot;
<strong>Output:</strong> &quot;H2MgO2&quot;
<strong>Explanation:</strong> The count of elements are {&#39;H&#39;: 2, &#39;Mg&#39;: 1, &#39;O&#39;: 2}.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> formula = &quot;K4(ON(SO3)2)2&quot;
<strong>Output:</strong> &quot;K4N2O14S4&quot;
<strong>Explanation:</strong> The count of elements are {&#39;K&#39;: 4, &#39;N&#39;: 2, &#39;O&#39;: 14, &#39;S&#39;: 4}.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= formula.length &lt;= 1000</code></li>
	<li><code>formula</code> consists of English letters, digits, <code>&#39;(&#39;</code>, and <code>&#39;)&#39;</code>.</li>
	<li><code>formula</code> is always valid.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> A chemical formula with parentheses and counts must be tallied and printed with atoms in sorted order. Length $\le 1000$, so a stack or a recursive descent both work.
>
> A multiplier applies to a whole parenthesized group. Scanning right to left, a count is seen before the group: on `)` push the current multiplier and multiply by the count; on `(` restore it.
>
> Accumulate this way: digits form $\textit{freq}$, and each element name (plus trailing lowercase letters) adds $\textit{freq}\times\textit{multiplier}$. Sort the keys and omit a count of $1$.

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
