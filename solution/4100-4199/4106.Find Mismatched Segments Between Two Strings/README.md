---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4106. 找出两个字符串的不匹配片段](https://leetcode.cn/problems/find-mismatched-segments-between-two-strings)

[English Version](/solution/4100-4199/4106.Find%20Mismatched%20Segments%20Between%20Two%20Strings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个长度相同的字符串 <code>s1</code> 和 <code>s2</code>。</p>

<p><strong>不匹配片段</strong>&nbsp;是一个&nbsp;<strong>极大连续</strong>&nbsp;下标区间，其中每个下标处 <code>s1</code> 和 <code>s2</code> 的字符都不相同。</p>

<p>返回一个二维数组，其中包含每个不匹配片段对应的 <code>[sub1, sub2]</code>，<code>sub1</code> 和 <code>sub2</code> 分别是 <code>s1</code> 和 <code>s2</code> 中对应的&nbsp;<strong><span data-keyword="substring-nonempty">子字符串</span></strong>。请按片段起始下标的&nbsp;<strong>递增</strong>&nbsp;顺序返回。如果没有不匹配片段，则返回一个空数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s1 = "earth", s2 = "empty"</span></p>

<p><strong>输出：</strong> <span class="example-io">[["ar","mp"],["h","y"]]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>下标 1 和 2 处的字符不同，形成不匹配片段 <code>["ar","mp"]</code>。</li>
	<li>下标 4 处的字符不同，形成不匹配片段 <code>["h","y"]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s1 = "action", s2 = "nation"</span></p>

<p><strong>输出：</strong> <span class="example-io">[["ac","na"]]</span></p>

<p><strong>解释：</strong></p>

<p>下标 0 和 1 处的字符不同，形成不匹配片段 <code>["ac","na"]</code>。两个字符串中其余位置的字符均相同。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s1 = "hot", s2 = "hot"</span></p>

<p><strong>输出：</strong> <span class="example-io">[]</span></p>

<p><strong>解释：</strong></p>

<p>两个字符串完全相同，因此没有不匹配片段。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length == s2.length &lt;= 100</code></li>
	<li><code>s1</code> 和 <code>s2</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

<!-- thinking:start -->

> **思考**
>
> $n$ 不超过 $100$，从每个起点向右找出连续不同的一段也能得到全部片段。这些片段互不重叠，相邻起点会重复扫到同一段。
>
> 片段必须是极大区间。某个位置一旦相同，当前片段就在这里结束，下一段只能从它的右侧开始。
>
> 相同字符把两个字符串切成互不交叉的若干段。从左到右用两个指针一起走一遍即可：先跳过相同字符，再把随后整段不同字符一次性取出。

<!-- thinking:end -->

我们从左到右同时扫描 $\textit{s1}$ 与 $\textit{s2}$，用指针 $i$ 表示当前下标。若 $\textit{s1}[i]=\textit{s2}[i]$，则把 $i$ 右移一位。否则令 $j=i+1$，并在 $j<n$ 且 $\textit{s1}[j]\neq\textit{s2}[j]$ 时持续右移 $j$。区间 $[i,j)$ 上每个位置的字符都不同，且 $i=0$ 或 $\textit{s1}[i-1]=\textit{s2}[i-1]$，同时 $j=n$ 或 $\textit{s1}[j]=\textit{s2}[j]$，因此这是一个极大不匹配片段。把 $\textit{s1}[i..j)$ 和 $\textit{s2}[i..j)$ 加入答案，再令 $i=j$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是字符串的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMismatchedSegments(self, s1: str, s2: str) -> list[list[str]]:
        ans = []
        n = len(s1)
        i = 0
        while i < n:
            if s1[i] == s2[i]:
                i += 1
                continue
            j = i + 1
            while j < n and s1[j] != s2[j]:
                j += 1
            ans.append([s1[i:j], s2[i:j]])
            i = j
        return ans
```

#### Java

```java
class Solution {
    public List<List<String>> findMismatchedSegments(String s1, String s2) {
        int n = s1.length();
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < n;) {
            if (s1.charAt(i) == s2.charAt(i)) {
                ++i;
                continue;
            }
            int j = i + 1;
            while (j < n && s1.charAt(j) != s2.charAt(j)) {
                ++j;
            }
            ans.add(List.of(s1.substring(i, j), s2.substring(i, j)));
            i = j;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<string>> findMismatchedSegments(string s1, string s2) {
        vector<vector<string>> ans;
        int n = s1.size();
        int i = 0;
        while (i < n) {
            if (s1[i] == s2[i]) {
                i++;
                continue;
            }
            int j = i + 1;
            while (j < n && s1[j] != s2[j]) {
                j++;
            }
            ans.push_back({s1.substr(i, j - i), s2.substr(i, j - i)});
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
func findMismatchedSegments(s1 string, s2 string) [][]string {
	ans := [][]string{}
	n := len(s1)
	i := 0
	for i < n {
		if s1[i] == s2[i] {
			i++
			continue
		}
		j := i + 1
		for j < n && s1[j] != s2[j] {
			j++
		}
		ans = append(ans, []string{s1[i:j], s2[i:j]})
		i = j
	}
	return ans
}
```

#### TypeScript

```ts
function findMismatchedSegments(s1: string, s2: string): string[][] {
    const ans: string[][] = [];
    const n = s1.length;
    let i = 0;
    while (i < n) {
        if (s1[i] === s2[i]) {
            i++;
            continue;
        }
        let j = i + 1;
        while (j < n && s1[j] !== s2[j]) {
            j++;
        }
        ans.push([s1.slice(i, j), s2.slice(i, j)]);
        i = j;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
