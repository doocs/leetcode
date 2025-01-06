---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3412.Find%20Mirror%20Score%20of%20a%20String/README_EN.md
---

<!-- problem:start -->

# [3412. Find Mirror Score of a String](https://leetcode.com/problems/find-mirror-score-of-a-string)

[中文文档](/solution/3400-3499/3412.Find%20Mirror%20Score%20of%20a%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code>.</p>

<p>We define the <strong>mirror</strong> of a letter in the English alphabet as its corresponding letter when the alphabet is reversed. For example, the mirror of <code>&#39;a&#39;</code> is <code>&#39;z&#39;</code>, and the mirror of <code>&#39;y&#39;</code> is <code>&#39;b&#39;</code>.</p>

<p>Initially, all characters in the string <code>s</code> are <strong>unmarked</strong>.</p>

<p>You start with a score of 0, and you perform the following process on the string <code>s</code>:</p>

<ul>
	<li>Iterate through the string from left to right.</li>
	<li>At each index <code>i</code>, find the closest <strong>unmarked</strong> index <code>j</code> such that <code>j &lt; i</code> and <code>s[j]</code> is the mirror of <code>s[i]</code>. Then, <strong>mark</strong> both indices <code>i</code> and <code>j</code>, and add the value <code>i - j</code> to the total score.</li>
	<li>If no such index <code>j</code> exists for the index <code>i</code>, move on to the next index without making any changes.</li>
</ul>

<p>Return the total score at the end of the process.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aczzx&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>i = 0</code>. There is no index <code>j</code> that satisfies the conditions, so we skip.</li>
	<li><code>i = 1</code>. There is no index <code>j</code> that satisfies the conditions, so we skip.</li>
	<li><code>i = 2</code>. The closest index <code>j</code> that satisfies the conditions is <code>j = 0</code>, so we mark both indices 0 and 2, and then add <code>2 - 0 = 2</code> to the score.</li>
	<li><code>i = 3</code>. There is no index <code>j</code> that satisfies the conditions, so we skip.</li>
	<li><code>i = 4</code>. The closest index <code>j</code> that satisfies the conditions is <code>j = 1</code>, so we mark both indices 1 and 4, and then add <code>4 - 1 = 3</code> to the score.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcdef&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>For each index <code>i</code>, there is no index <code>j</code> that satisfies the conditions.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can use a hash table $\textit{d}$ to store the index list of each unmarked character, where the key is the character and the value is the list of indices.

We traverse the string $\textit{s}$, and for each character $\textit{x}$, we find its mirror character $\textit{y}$. If $\textit{d}$ contains $\textit{y}$, we take out the index list $\textit{ls}$ corresponding to $\textit{y}$, take out the last element $\textit{j}$ from $\textit{ls}$, and remove $\textit{j}$ from $\textit{ls}$. If $\textit{ls}$ becomes empty, we remove $\textit{y}$ from $\textit{d}$. At this point, we have found a pair of indices $(\textit{j}, \textit{i})$ that meet the condition, and we add $\textit{i} - \textit{j}$ to the answer. Otherwise, we add $\textit{x}$ to $\textit{d}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $\textit{s}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def calculateScore(self, s: str) -> int:
        d = defaultdict(list)
        ans = 0
        for i, x in enumerate(s):
            y = chr(ord("a") + ord("z") - ord(x))
            if d[y]:
                j = d[y].pop()
                ans += i - j
            else:
                d[x].append(i)
        return ans
```

#### Java

```java
class Solution {
    public long calculateScore(String s) {
        Map<Character, List<Integer>> d = new HashMap<>(26);
        int n = s.length();
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            char x = s.charAt(i);
            char y = (char) ('a' + 'z' - x);
            if (d.containsKey(y)) {
                var ls = d.get(y);
                int j = ls.remove(ls.size() - 1);
                if (ls.isEmpty()) {
                    d.remove(y);
                }
                ans += i - j;
            } else {
                d.computeIfAbsent(x, k -> new ArrayList<>()).add(i);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long calculateScore(string s) {
        unordered_map<char, vector<int>> d;
        int n = s.length();
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            char x = s[i];
            char y = 'a' + 'z' - x;
            if (d.contains(y)) {
                vector<int>& ls = d[y];
                int j = ls.back();
                ls.pop_back();
                if (ls.empty()) {
                    d.erase(y);
                }
                ans += i - j;
            } else {
                d[x].push_back(i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func calculateScore(s string) (ans int64) {
	d := make(map[rune][]int)
	for i, x := range s {
		y := 'a' + 'z' - x
		if ls, ok := d[y]; ok {
			j := ls[len(ls)-1]
			d[y] = ls[:len(ls)-1]
			if len(d[y]) == 0 {
				delete(d, y)
			}
			ans += int64(i - j)
		} else {
			d[x] = append(d[x], i)
		}
	}
	return
}
```

#### TypeScript

```ts
function calculateScore(s: string): number {
    const d: Map<string, number[]> = new Map();
    const n = s.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        const x = s[i];
        const y = String.fromCharCode('a'.charCodeAt(0) + 'z'.charCodeAt(0) - x.charCodeAt(0));

        if (d.has(y)) {
            const ls = d.get(y)!;
            const j = ls.pop()!;
            if (ls.length === 0) {
                d.delete(y);
            }
            ans += i - j;
        } else {
            if (!d.has(x)) {
                d.set(x, []);
            }
            d.get(x)!.push(i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
