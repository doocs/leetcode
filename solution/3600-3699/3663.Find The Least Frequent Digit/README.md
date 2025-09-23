---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3663.Find%20The%20Least%20Frequent%20Digit/README.md
rating: 1284
source: 第 164 场双周赛 Q1
---

<!-- problem:start -->

# [3663. 出现频率最低的数字](https://leetcode.cn/problems/find-the-least-frequent-digit)

[English Version](/solution/3600-3699/3663.Find%20The%20Least%20Frequent%20Digit/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>，找出在其十进制表示中出现频率&nbsp;<strong>最低&nbsp;</strong>的数字。如果多个数字的出现频率相同，则选择&nbsp;<strong>最小&nbsp;</strong>的那个数字。</p>

<p>以整数形式返回所选的数字。</p>

<p>数字 <code>x</code> 的出现频率是指它在&nbsp;<code>n</code> 的十进制表示中的出现次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 1553322</span></p>

<p><strong>输出：</strong> 1</p>

<p><strong>解释：</strong></p>

<p>在 <code>n</code> 中，出现频率最低的数字是 1，它只出现了一次。所有其他数字都出现了两次。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 723344511</span></p>

<p><strong>输出：</strong> 2</p>

<p><strong>解释：</strong></p>

<p>在 <code>n</code> 中，出现频率最低的数字是 7、2 和 5，它们都只出现了一次。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们用一个数组 $\textit{cnt}$ 来统计每个数字出现的频率。遍历数字 $n$ 的每一位，更新 $\textit{cnt}$ 数组。

然后，我们用一个变量 $f$ 来记录当前出现频率最低的数字的频率，以及一个变量 $\textit{ans}$ 来记录对应的数字。

接下来，我们遍历 $\textit{cnt}$ 数组，如果 $0 \lt \textit{cnt}[x] \lt f$，说明我们找到了出现频率更低的数字，更新 $f = \textit{cnt}[x]$，以及 $\textit{ans} = x$。

遍历结束后，返回答案 $\textit{ans}$ 即可。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getLeastFrequentDigit(self, n: int) -> int:
        cnt = [0] * 10
        while n:
            n, x = divmod(n, 10)
            cnt[x] += 1
        ans, f = 0, inf
        for x, v in enumerate(cnt):
            if 0 < v < f:
                f = v
                ans = x
        return ans
```

#### Java

```java
class Solution {
    public int getLeastFrequentDigit(int n) {
        int[] cnt = new int[10];
        for (; n > 0; n /= 10) {
            ++cnt[n % 10];
        }
        int ans = 0, f = 1 << 30;
        for (int x = 0; x < 10; ++x) {
            if (cnt[x] > 0 && cnt[x] < f) {
                f = cnt[x];
                ans = x;
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
    int getLeastFrequentDigit(int n) {
        int cnt[10]{};
        for (; n > 0; n /= 10) {
            ++cnt[n % 10];
        }
        int ans = 0, f = 1 << 30;
        for (int x = 0; x < 10; ++x) {
            if (cnt[x] > 0 && cnt[x] < f) {
                f = cnt[x];
                ans = x;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func getLeastFrequentDigit(n int) (ans int) {
	cnt := [10]int{}
	for ; n > 0; n /= 10 {
		cnt[n%10]++
	}
	f := 1 << 30
	for x, v := range cnt {
		if v > 0 && v < f {
			f = v
			ans = x
		}
	}
	return
}
```

#### TypeScript

```ts
function getLeastFrequentDigit(n: number): number {
    const cnt: number[] = Array(10).fill(0);
    for (; n; n = (n / 10) | 0) {
        cnt[n % 10]++;
    }
    let [ans, f] = [0, Number.MAX_SAFE_INTEGER];
    for (let x = 0; x < 10; ++x) {
        if (cnt[x] > 0 && cnt[x] < f) {
            f = cnt[x];
            ans = x;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
