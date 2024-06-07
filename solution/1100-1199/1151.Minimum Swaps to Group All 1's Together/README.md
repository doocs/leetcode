---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1151.Minimum%20Swaps%20to%20Group%20All%201%27s%20Together/README.md
rating: 1508
source: 第 6 场双周赛 Q2
tags:
    - 数组
    - 滑动窗口
---

<!-- problem:start -->

# [1151. 最少交换次数来组合所有的 1 🔒](https://leetcode.cn/problems/minimum-swaps-to-group-all-1s-together)

[English Version](/solution/1100-1199/1151.Minimum%20Swaps%20to%20Group%20All%201%27s%20Together/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给出一个二进制数组&nbsp;<code>data</code>，你需要通过交换位置，将数组中 <strong>任何位置</strong> 上的 1 组合到一起，并返回所有可能中所需&nbsp;<strong>最少的交换次数</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> data = [1,0,1,0,1]
<strong>输出:</strong> 1
<strong>解释: </strong>
有三种可能的方法可以把所有的 1 组合在一起：
[1,1,1,0,0]，交换 1 次；
[0,1,1,1,0]，交换 2 次；
[0,0,1,1,1]，交换 1 次。
所以最少的交换次数为 1。
</pre>

<p><strong>示例&nbsp; 2:</strong></p>

<pre>
<strong>输入：</strong>data =&nbsp;[0,0,0,1,0]
<strong>输出：</strong>0
<strong>解释： </strong>
由于数组中只有一个 1，所以不需要交换。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入：</strong>data =&nbsp;[1,0,1,0,1,0,0,1,1,0,1]
<strong>输出：3
解释：
</strong>交换 3 次，一种可行的只用 3 次交换的解决方案是 [0,0,0,0,0,1,1,1,1,1,1]。
</pre>

<p><strong>示例 4:</strong></p>

<pre>
<strong>输入:</strong> data = [1,0,1,0,1,0,1,1,1,0,1,0,0,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1]
<strong>输出:</strong> 8
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= data.length &lt;= 10<sup>5</sup></code></li>
	<li><code>data[i]</code>&nbsp;==&nbsp;<code>0</code>&nbsp;or&nbsp;<code>1</code>.</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口

我们先统计数组中 $1$ 的个数，记为 $k$。然后我们使用滑动窗口，窗口大小为 $k$，窗口右边界从左向右移动，统计窗口内 $1$ 的个数，记为 $t$。每次移动窗口时，都更新 $t$ 的值，最后窗口右边界移动到数组末尾时，窗口内 $1$ 的个数最多，记为 $mx$。最后答案为 $k - mx$。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSwaps(self, data: List[int]) -> int:
        k = data.count(1)
        mx = t = sum(data[:k])
        for i in range(k, len(data)):
            t += data[i]
            t -= data[i - k]
            mx = max(mx, t)
        return k - mx
```

#### Java

```java
class Solution {
    public int minSwaps(int[] data) {
        int k = 0;
        for (int v : data) {
            k += v;
        }
        int t = 0;
        for (int i = 0; i < k; ++i) {
            t += data[i];
        }
        int mx = t;
        for (int i = k; i < data.length; ++i) {
            t += data[i];
            t -= data[i - k];
            mx = Math.max(mx, t);
        }
        return k - mx;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSwaps(vector<int>& data) {
        int k = 0;
        for (int& v : data) {
            k += v;
        }
        int t = 0;
        for (int i = 0; i < k; ++i) {
            t += data[i];
        }
        int mx = t;
        for (int i = k; i < data.size(); ++i) {
            t += data[i];
            t -= data[i - k];
            mx = max(mx, t);
        }
        return k - mx;
    }
};
```

#### Go

```go
func minSwaps(data []int) int {
	k := 0
	for _, v := range data {
		k += v
	}
	t := 0
	for _, v := range data[:k] {
		t += v
	}
	mx := t
	for i := k; i < len(data); i++ {
		t += data[i]
		t -= data[i-k]
		mx = max(mx, t)
	}
	return k - mx
}
```

#### TypeScript

```ts
function minSwaps(data: number[]): number {
    const k = data.reduce((acc, cur) => acc + cur, 0);
    let t = data.slice(0, k).reduce((acc, cur) => acc + cur, 0);
    let mx = t;
    for (let i = k; i < data.length; ++i) {
        t += data[i] - data[i - k];
        mx = Math.max(mx, t);
    }
    return k - mx;
}
```

#### C#

```cs
public class Solution {
    public int MinSwaps(int[] data) {
        int k = data.Count(x => x == 1);
        int t = data.Take(k).Sum();
        int mx = t;
        for (int i = k; i < data.Length; ++i) {
            t += data[i];
            t -= data[i - k];
            mx = Math.Max(mx, t);
        }
        return k - mx;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
