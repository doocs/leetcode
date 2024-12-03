---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3361.Shift%20Distance%20Between%20Two%20Strings/README.md
tags:
    - 数组
    - 字符串
    - 前缀和
---

<!-- problem:start -->

# [3361. 两个字符串的切换距离](https://leetcode.cn/problems/shift-distance-between-two-strings)

[English Version](/solution/3300-3399/3361.Shift%20Distance%20Between%20Two%20Strings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个长度相同的字符串&nbsp;<code>s</code> 和&nbsp;<code>t</code>&nbsp;，以及两个整数数组&nbsp;<code>nextCost</code> 和&nbsp;<code>previousCost</code>&nbsp;。</p>

<p>一次操作中，你可以选择 <code>s</code>&nbsp;中的一个下标 <code>i</code>&nbsp;，执行以下操作 <strong>之一</strong>&nbsp;：</p>

<ul>
	<li>将&nbsp;<code>s[i]</code>&nbsp;切换为字母表中的下一个字母，如果&nbsp;<code>s[i] == 'z'</code>&nbsp;，切换后得到&nbsp;<code>'a'</code>&nbsp;。操作的代价为&nbsp;<code>nextCost[j]</code>&nbsp;，其中&nbsp;<code>j</code>&nbsp;表示&nbsp;<code>s[i]</code>&nbsp;在字母表中的下标。</li>
	<li>将&nbsp;<code>s[i]</code>&nbsp;切换为字母表中的上一个字母，如果&nbsp;<code>s[i] == 'a'</code>&nbsp;，切换后得到&nbsp;<code>'z'</code>&nbsp;。操作的代价为&nbsp;<code>previousCost[j]</code>&nbsp;，其中&nbsp;<code>j</code> 是&nbsp;<code>s[i]</code>&nbsp;在字母表中的下标。</li>
</ul>

<p><strong>切换距离</strong>&nbsp;指的是将字符串 <code>s</code>&nbsp;变为字符串 <code>t</code>&nbsp;的 <strong>最少</strong>&nbsp;操作代价总和。</p>

<p>请你返回从 <code>s</code>&nbsp;到 <code>t</code>&nbsp;的 <strong>切换距离</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "abab", t = "baba", nextCost = [100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0], previousCost = [1,100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><b>解释：</b></p>

<ul>
	<li>选择下标&nbsp;<code>i = 0</code>&nbsp;并将&nbsp;<code>s[0]</code>&nbsp;向前切换 25 次，总代价为 1 。</li>
	<li>选择下标&nbsp;<code>i = 1</code>&nbsp;并将&nbsp;<code>s[1]</code>&nbsp;向后切换 25 次，总代价为 0 。</li>
	<li>选择下标&nbsp;<code>i = 2</code>&nbsp;并将&nbsp;<code>s[2]</code>&nbsp;向前切换 25 次，总代价为 1 。</li>
	<li>选择下标&nbsp;<code>i = 3</code>&nbsp;并将&nbsp;<code>s[3]</code>&nbsp;向后切换 25 次，总代价为 0 。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "leet", t = "code", nextCost = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1], previousCost = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]</span></p>

<p><span class="example-io"><b>输出：</b>31</span></p>

<p><b>解释：</b></p>

<ul>
	<li>选择下标&nbsp;<code>i = 0</code>&nbsp;并将&nbsp;<code>s[0]</code>&nbsp;向前切换 9 次，总代价为 9 。</li>
	<li>选择下标&nbsp;<code>i = 1</code>&nbsp;并将&nbsp;<code>s[1]</code>&nbsp;向后切换 10 次，总代价为 10 。</li>
	<li>选择下标&nbsp;<code>i = 2</code> 并将&nbsp;<code>s[2]</code>&nbsp;向前切换 1 次，总代价为 1 。</li>
	<li>选择下标 <code>i = 3</code> 并将&nbsp;<code>s[3]</code>&nbsp;向后切换 11 次，总代价为 11 。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length == t.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 和&nbsp;<code>t</code>&nbsp;都只包含小写英文字母。</li>
	<li><code>nextCost.length == previousCost.length == 26</code></li>
	<li><code>0 &lt;= nextCost[i], previousCost[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def shiftDistance(
        self, s: str, t: str, nextCost: List[int], previousCost: List[int]
    ) -> int:
        m = 26
        s1 = [0] * (m << 1 | 1)
        s2 = [0] * (m << 1 | 1)
        for i in range(m << 1):
            s1[i + 1] = s1[i] + nextCost[i % m]
            s2[i + 1] = s2[i] + previousCost[(i + 1) % m]
        ans = 0
        for a, b in zip(s, t):
            x, y = ord(a) - ord("a"), ord(b) - ord("a")
            c1 = s1[y + m if y < x else y] - s1[x]
            c2 = s2[x + m if x < y else x] - s2[y]
            ans += min(c1, c2)
        return ans
```

#### Java

```java
class Solution {
    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        int m = 26;
        long[] s1 = new long[(m << 1) + 1];
        long[] s2 = new long[(m << 1) + 1];
        for (int i = 0; i < (m << 1); i++) {
            s1[i + 1] = s1[i] + nextCost[i % m];
            s2[i + 1] = s2[i] + previousCost[(i + 1) % m];
        }
        long ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int x = s.charAt(i) - 'a';
            int y = t.charAt(i) - 'a';
            long c1 = s1[y + (y < x ? m : 0)] - s1[x];
            long c2 = s2[x + (x < y ? m : 0)] - s2[y];
            ans += Math.min(c1, c2);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long shiftDistance(string s, string t, vector<int>& nextCost, vector<int>& previousCost) {
        int m = 26;
        vector<long long> s1((m << 1) + 1);
        vector<long long> s2((m << 1) + 1);
        for (int i = 0; i < (m << 1); ++i) {
            s1[i + 1] = s1[i] + nextCost[i % m];
            s2[i + 1] = s2[i] + previousCost[(i + 1) % m];
        }

        long long ans = 0;
        for (int i = 0; i < s.size(); ++i) {
            int x = s[i] - 'a';
            int y = t[i] - 'a';
            long long c1 = s1[y + (y < x ? m : 0)] - s1[x];
            long long c2 = s2[x + (x < y ? m : 0)] - s2[y];
            ans += min(c1, c2);
        }

        return ans;
    }
};
```

#### Go

```go
func shiftDistance(s string, t string, nextCost []int, previousCost []int) (ans int64) {
	m := 26
	s1 := make([]int64, (m<<1)+1)
	s2 := make([]int64, (m<<1)+1)
	for i := 0; i < (m << 1); i++ {
		s1[i+1] = s1[i] + int64(nextCost[i%m])
		s2[i+1] = s2[i] + int64(previousCost[(i+1)%m])
	}
	for i := 0; i < len(s); i++ {
		x := int(s[i] - 'a')
		y := int(t[i] - 'a')
		z := y
		if y < x {
			z += m
		}
		c1 := s1[z] - s1[x]
		z = x
		if x < y {
			z += m
		}
		c2 := s2[z] - s2[y]
		ans += min(c1, c2)
	}
	return
}
```

#### TypeScript

```ts
function shiftDistance(s: string, t: string, nextCost: number[], previousCost: number[]): number {
    const m = 26;
    const s1: number[] = Array((m << 1) + 1).fill(0);
    const s2: number[] = Array((m << 1) + 1).fill(0);
    for (let i = 0; i < m << 1; i++) {
        s1[i + 1] = s1[i] + nextCost[i % m];
        s2[i + 1] = s2[i] + previousCost[(i + 1) % m];
    }
    let ans = 0;
    const a = 'a'.charCodeAt(0);
    for (let i = 0; i < s.length; i++) {
        const x = s.charCodeAt(i) - a;
        const y = t.charCodeAt(i) - a;
        const c1 = s1[y + (y < x ? m : 0)] - s1[x];
        const c2 = s2[x + (x < y ? m : 0)] - s2[y];
        ans += Math.min(c1, c2);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
