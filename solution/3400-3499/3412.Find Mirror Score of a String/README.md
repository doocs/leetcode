---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3412.Find%20Mirror%20Score%20of%20a%20String/README.md
rating: 1578
source: 第 431 场周赛 Q2
tags:
    - 栈
    - 哈希表
    - 字符串
    - 模拟
---

<!-- problem:start -->

# [3412. 计算字符串的镜像分数](https://leetcode.cn/problems/find-mirror-score-of-a-string)

[English Version](/solution/3400-3499/3412.Find%20Mirror%20Score%20of%20a%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code>。</p>

<p>英文字母中每个字母的&nbsp;<strong>镜像&nbsp;</strong>定义为反转字母表之后对应位置上的字母。例如，<code>'a'</code> 的镜像是 <code>'z'</code>，<code>'y'</code> 的镜像是 <code>'b'</code>。</p>

<p>最初，字符串 <code>s</code> 中的所有字符都&nbsp;<strong>未标记&nbsp;</strong>。</p>

<p>字符串 <code>s</code>&nbsp;的初始分数为 0 ，你需要对其执行以下过程：</p>

<ul>
	<li>从左到右遍历字符串。</li>
	<li>对于每个下标&nbsp;<code>i&nbsp;</code>，找到距离最近的&nbsp;<strong>未标记</strong> 下标&nbsp;<code>j</code>，下标 <code>j</code> 需要满足&nbsp;<code>j &lt; i</code> 且 <code>s[j]</code> 是 <code>s[i]</code> 的镜像。然后&nbsp;<strong>标记</strong> 下标&nbsp;<code>i</code> 和 <code>j</code>，总分加上&nbsp;<code>i - j</code>&nbsp;的值。</li>
	<li>如果对于下标&nbsp;<code>i</code>，不存在满足条件的下标&nbsp;<code>j</code>，则跳过该下标，继续处理下一个下标，不需要进行标记。</li>
</ul>

<p>返回最终的总分。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "aczzx"</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>i = 0</code>。没有符合条件的下标&nbsp;<code>j</code>，跳过。</li>
	<li><code>i = 1</code>。没有符合条件的下标&nbsp;<code>j</code>，跳过。</li>
	<li><code>i = 2</code>。距离最近的符合条件的下标是 <code>j = 0</code>，因此标记下标&nbsp;0 和 2，然后将总分加上&nbsp;<code>2 - 0 = 2</code>&nbsp;。</li>
	<li><code>i = 3</code>。没有符合条件的下标&nbsp;<code>j</code>，跳过。</li>
	<li><code>i = 4</code>。距离最近的符合条件的下标是 <code>j = 1</code>，因此标记下标&nbsp;1 和 4，然后将总分加上&nbsp;<code>4 - 1 = 3</code>&nbsp;。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abcdef"</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>对于每个下标&nbsp;<code>i</code>，都不存在满足条件的下标&nbsp;<code>j</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们可以用一个哈希表 $\textit{d}$ 来存储每个未标记的字符的下标列表，其中键是字符，值是下标列表。

我们遍历字符串 $\textit{s}$，对于每个字符 $\textit{x}$，我们找到其镜像字符 $\textit{y}$，如果 $\textit{d}$ 中存在 $\textit{y}$，我们就取出 $\textit{y}$ 对应的下标列表 $\textit{ls}$，取出 $\textit{ls}$ 的最后一个元素 $\textit{j}$，并将 $\textit{j}$ 从 $\textit{ls}$ 中移除。如果 $\textit{ls}$ 变为空，我们就将 $\textit{y}$ 从 $\textit{d}$ 中移除。此时，我们就找到了一个满足条件的下标对 $(\textit{j}, \textit{i})$，并将 $\textit{i} - \textit{j}$ 加到答案中。否则，我们将 $\textit{x}$ 加入到 $\textit{d}$ 中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $\textit{s}$ 的长度。

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
