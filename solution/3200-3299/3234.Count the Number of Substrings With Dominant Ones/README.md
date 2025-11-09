---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3234.Count%20the%20Number%20of%20Substrings%20With%20Dominant%20Ones/README.md
rating: 2556
source: 第 408 场周赛 Q3
tags:
    - 字符串
    - 枚举
    - 滑动窗口
---

<!-- problem:start -->

# [3234. 统计 1 显著的字符串的数量](https://leetcode.cn/problems/count-the-number-of-substrings-with-dominant-ones)

[English Version](/solution/3200-3299/3234.Count%20the%20Number%20of%20Substrings%20With%20Dominant%20Ones/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二进制字符串 <code>s</code>。</p>

<p>请你统计并返回其中 <strong>1 显著 </strong> 的 <span data-keyword="substring-nonempty">子字符串</span> 的数量。</p>

<p>如果字符串中 1 的数量 <strong>大于或等于</strong> 0 的数量的 <strong>平方</strong>，则认为该字符串是一个 <strong>1 显著</strong> 的字符串 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "00011"</span></p>

<p><strong>输出：</strong><span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>1 显著的子字符串如下表所示。</p>
</div>

<table>
	<thead>
		<tr>
			<th>i</th>
			<th>j</th>
			<th>s[i..j]</th>
			<th>0 的数量</th>
			<th>1 的数量</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>3</td>
			<td>3</td>
			<td>1</td>
			<td>0</td>
			<td>1</td>
		</tr>
		<tr>
			<td>4</td>
			<td>4</td>
			<td>1</td>
			<td>0</td>
			<td>1</td>
		</tr>
		<tr>
			<td>2</td>
			<td>3</td>
			<td>01</td>
			<td>1</td>
			<td>1</td>
		</tr>
		<tr>
			<td>3</td>
			<td>4</td>
			<td>11</td>
			<td>0</td>
			<td>2</td>
		</tr>
		<tr>
			<td>2</td>
			<td>4</td>
			<td>011</td>
			<td>1</td>
			<td>2</td>
		</tr>
	</tbody>
</table>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "101101"</span></p>

<p><strong>输出：</strong><span class="example-io">16</span></p>

<p><strong>解释：</strong></p>

<p>1 不显著的子字符串如下表所示。</p>

<p>总共有 21 个子字符串，其中 5 个是 1 不显著字符串，因此有 16 个 1 显著子字符串。</p>
</div>

<table>
	<thead>
		<tr>
			<th>i</th>
			<th>j</th>
			<th>s[i..j]</th>
			<th>0 的数量</th>
			<th>1 的数量</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>1</td>
			<td>1</td>
			<td>0</td>
			<td>1</td>
			<td>0</td>
		</tr>
		<tr>
			<td>4</td>
			<td>4</td>
			<td>0</td>
			<td>1</td>
			<td>0</td>
		</tr>
		<tr>
			<td>1</td>
			<td>4</td>
			<td>0110</td>
			<td>2</td>
			<td>2</td>
		</tr>
		<tr>
			<td>0</td>
			<td>4</td>
			<td>10110</td>
			<td>2</td>
			<td>3</td>
		</tr>
		<tr>
			<td>1</td>
			<td>5</td>
			<td>01101</td>
			<td>2</td>
			<td>3</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>s</code> 仅包含字符 <code>'0'</code> 和 <code>'1'</code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：预处理 + 枚举

根据题目描述，显著字符串满足 $\textit{cnt}_1 \geq \textit{cnt}_0^2$，那么 $\textit{cnt}_0$ 的最大值不超过 $\sqrt{n}$，其中 $n$ 是字符串的长度。因此我们可以枚举 $\textit{cnt}_0$ 的值，然后计算满足条件的子字符串数量。

我们首先预处理字符串中每个位置开始的第一个 $0$ 的位置，存储在数组 $\textit{nxt}$ 中，其中 $\textit{nxt}[i]$ 表示从位置 $i$ 开始的第一个 $0$ 的位置，如果不存在则为 $n$。

接下来，我们遍历字符串的每个位置 $i$ 作为子字符串的起始位置，初始化 $\textit{cnt}_0$ 的值为 $0$ 或 $1$（取决于当前位置是否为 $0$）。然后我们使用一个指针 $j$ 从位置 $i$ 开始，逐步跳转到下一个 $0$ 的位置，同时更新 $\textit{cnt}_0$ 的值。

对于从位置 $i$ 开始，且包含 $\textit{cnt}_0$ 个 $0$ 的子字符串，最多可以包含 $\textit{nxt}[j + 1] - i - \textit{cnt}_0$ 个 $1$。如果这个数量大于等于 $\textit{cnt}_0^2$，则说明存在满足条件的子字符串。此时需要判断字符串从右端点 $\textit{nxt}[j + 1] - 1$ 向左移动多少个位置，可以使得子字符串仍然满足条件。具体来说，右端点一共可以有 $\min(\textit{nxt}[j + 1] - j, \textit{cnt}_1 - \textit{cnt}_0^2 + 1)$ 种选择方式。我们将这些数量累加到答案中。然后将指针 $j$ 移动到下一个 $0$ 的位置，继续枚举下一个 $\textit{cnt}_0$ 的值，直到 $\textit{cnt}_0^2$ 超过字符串长度为止。

时间复杂度 $O(n \times \sqrt{n})$，空间复杂度 $O(n)$，其中 $n$ 是字符串的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfSubstrings(self, s: str) -> int:
        n = len(s)
        nxt = [n] * (n + 1)
        for i in range(n - 1, -1, -1):
            nxt[i] = nxt[i + 1]
            if s[i] == "0":
                nxt[i] = i
        ans = 0
        for i in range(n):
            cnt0 = int(s[i] == "0")
            j = i
            while j < n and cnt0 * cnt0 <= n:
                cnt1 = (nxt[j + 1] - i) - cnt0
                if cnt1 >= cnt0 * cnt0:
                    ans += min(nxt[j + 1] - j, cnt1 - cnt0 * cnt0 + 1)
                j = nxt[j + 1]
                cnt0 += 1
        return ans
```

#### Java

```java
class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] nxt = new int[n + 1];
        nxt[n] = n;
        for (int i = n - 1; i >= 0; --i) {
            nxt[i] = nxt[i + 1];
            if (s.charAt(i) == '0') {
                nxt[i] = i;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int cnt0 = s.charAt(i) == '0' ? 1 : 0;
            int j = i;
            while (j < n && 1L * cnt0 * cnt0 <= n) {
                int cnt1 = nxt[j + 1] - i - cnt0;
                if (cnt1 >= cnt0 * cnt0) {
                    ans += Math.min(nxt[j + 1] - j, cnt1 - cnt0 * cnt0 + 1);
                }
                j = nxt[j + 1];
                ++cnt0;
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
    int numberOfSubstrings(string s) {
        int n = s.size();
        vector<int> nxt(n + 1);
        nxt[n] = n;
        for (int i = n - 1; i >= 0; --i) {
            nxt[i] = nxt[i + 1];
            if (s[i] == '0') {
                nxt[i] = i;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int cnt0 = s[i] == '0' ? 1 : 0;
            int j = i;
            while (j < n && 1LL * cnt0 * cnt0 <= n) {
                int cnt1 = nxt[j + 1] - i - cnt0;
                if (cnt1 >= cnt0 * cnt0) {
                    ans += min(nxt[j + 1] - j, cnt1 - cnt0 * cnt0 + 1);
                }
                j = nxt[j + 1];
                ++cnt0;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfSubstrings(s string) int {
	n := len(s)
	nxt := make([]int, n+1)
	nxt[n] = n
	for i := n - 1; i >= 0; i-- {
		nxt[i] = nxt[i+1]
		if s[i] == '0' {
			nxt[i] = i
		}
	}
	ans := 0
	for i := 0; i < n; i++ {
		cnt0 := 0
		if s[i] == '0' {
			cnt0 = 1
		}
		j := i
		for j < n && int64(cnt0*cnt0) <= int64(n) {
			cnt1 := nxt[j+1] - i - cnt0
			if cnt1 >= cnt0*cnt0 {
				ans += min(nxt[j+1]-j, cnt1-cnt0*cnt0+1)
			}
			j = nxt[j+1]
			cnt0++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function numberOfSubstrings(s: string): number {
    const n = s.length;
    const nxt: number[] = Array(n + 1).fill(0);
    nxt[n] = n;
    for (let i = n - 1; i >= 0; --i) {
        nxt[i] = nxt[i + 1];
        if (s[i] === '0') {
            nxt[i] = i;
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let cnt0 = s[i] === '0' ? 1 : 0;
        let j = i;
        while (j < n && cnt0 * cnt0 <= n) {
            const cnt1 = nxt[j + 1] - i - cnt0;
            if (cnt1 >= cnt0 * cnt0) {
                ans += Math.min(nxt[j + 1] - j, cnt1 - cnt0 * cnt0 + 1);
            }
            j = nxt[j + 1];
            ++cnt0;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
