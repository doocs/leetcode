---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2222.Number%20of%20Ways%20to%20Select%20Buildings/README.md
rating: 1656
source: 第 75 场双周赛 Q3
tags:
    - 字符串
    - 动态规划
    - 前缀和
---

<!-- problem:start -->

# [2222. 选择建筑的方案数](https://leetcode.cn/problems/number-of-ways-to-select-buildings)

[English Version](/solution/2200-2299/2222.Number%20of%20Ways%20to%20Select%20Buildings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的二进制字符串&nbsp;<code>s</code>&nbsp;，它表示一条街沿途的建筑类型，其中：</p>

<ul>
	<li><code>s[i] = '0'</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;栋建筑是一栋办公楼，</li>
	<li><code>s[i] = '1'</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;栋建筑是一间餐厅。</li>
</ul>

<p>作为市政厅的官员，你需要随机<strong>&nbsp;选择</strong>&nbsp;3 栋建筑。然而，为了确保多样性，选出来的 3 栋建筑 <strong>相邻</strong>&nbsp;的两栋不能是同一类型。</p>

<ul>
	<li>比方说，给你&nbsp;<code>s = "0<em><strong>0</strong></em>1<em><strong>1</strong></em>0<em><strong>1</strong></em>"</code>&nbsp;，我们不能选择第&nbsp;<code>1</code>&nbsp;，<code>3</code>&nbsp;和&nbsp;<code>5</code>&nbsp;栋建筑，因为得到的子序列是&nbsp;<code>"0<em><strong>11</strong></em>"</code>&nbsp;，有相邻两栋建筑是同一类型，所以 <strong>不合</strong>&nbsp;题意。</li>
</ul>

<p>请你返回可以选择 3 栋建筑的 <strong>有效方案数</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "001101"
<b>输出：</b>6
<b>解释：</b>
以下下标集合是合法的：
- [0,2,4] ，从 "<em><strong>0</strong></em>0<em><strong>1</strong></em>1<em><strong>0</strong></em>1" 得到 "010"
- [0,3,4] ，从 "<em><strong>0</strong></em>01<em><strong>10</strong></em>1" 得到 "010"
- [1,2,4] ，从 "0<em><strong>01</strong></em>1<em><strong>0</strong></em>1" 得到 "010"
- [1,3,4] ，从 "0<em><strong>0</strong></em>1<em><strong>10</strong></em>1" 得到 "010"
- [2,4,5] ，从 "00<em><strong>1</strong></em>1<em><strong>01</strong></em>" 得到 "101"
- [3,4,5] ，从 "001<em><strong>101</strong></em>" 得到 "101"
没有别的合法选择，所以总共有 6 种方法。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "11100"
<b>输出：</b>0
<b>解释：</b>没有任何符合题意的选择。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code>&nbsp;要么是&nbsp;<code>'0'</code>&nbsp;，要么是&nbsp;<code>'1'</code>&nbsp;。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 枚举

根据题目描述，我们需要选择 $3$ 栋建筑，且相邻的两栋不能是同一类型。

我们可以枚举中间的建筑，假设为 $x$，那么左右两边的建筑类型只能是 $x \oplus 1$，其中 $\oplus$ 表示异或运算。因此，我们可以使用两个数组 $l$ 和 $r$ 分别记录左右两边的建筑类型的数量，然后枚举中间的建筑，计算答案即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfWays(self, s: str) -> int:
        l = [0, 0]
        r = [s.count("0"), s.count("1")]
        ans = 0
        for x in map(int, s):
            r[x] -= 1
            ans += l[x ^ 1] * r[x ^ 1]
            l[x] += 1
        return ans
```

#### Java

```java
class Solution {
    public long numberOfWays(String s) {
        int n = s.length();
        int[] l = new int[2];
        int[] r = new int[2];
        for (int i = 0; i < n; ++i) {
            r[s.charAt(i) - '0']++;
        }
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = s.charAt(i) - '0';
            r[x]--;
            ans += 1L * l[x ^ 1] * r[x ^ 1];
            l[x]++;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long numberOfWays(string s) {
        int n = s.size();
        int l[2]{};
        int r[2]{};
        r[0] = ranges::count(s, '0');
        r[1] = n - r[0];
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = s[i] - '0';
            r[x]--;
            ans += 1LL * l[x ^ 1] * r[x ^ 1];
            l[x]++;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfWays(s string) (ans int64) {
	n := len(s)
	l := [2]int{}
	r := [2]int{}
	r[0] = strings.Count(s, "0")
	r[1] = n - r[0]
	for _, c := range s {
		x := int(c - '0')
		r[x]--
		ans += int64(l[x^1] * r[x^1])
		l[x]++
	}
	return
}
```

#### TypeScript

```ts
function numberOfWays(s: string): number {
    const n = s.length;
    const l: number[] = [0, 0];
    const r: number[] = [s.split('').filter(c => c === '0').length, 0];
    r[1] = n - r[0];
    let ans: number = 0;
    for (const c of s) {
        const x = c === '0' ? 0 : 1;
        r[x]--;
        ans += l[x ^ 1] * r[x ^ 1];
        l[x]++;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
