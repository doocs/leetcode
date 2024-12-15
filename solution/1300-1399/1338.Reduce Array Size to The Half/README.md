---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1338.Reduce%20Array%20Size%20to%20The%20Half/README.md
rating: 1303
source: 第 174 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 哈希表
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [1338. 数组大小减半](https://leetcode.cn/problems/reduce-array-size-to-the-half)

[English Version](/solution/1300-1399/1338.Reduce%20Array%20Size%20to%20The%20Half/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>arr</code>。你可以从中选出一个整数集合，并删除这些整数在数组中的每次出现。</p>

<p>返回&nbsp;<strong>至少</strong>&nbsp;能删除数组中的一半整数的整数集合的最小大小。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [3,3,3,3,5,5,5,2,2,7]
<strong>输出：</strong>2
<strong>解释：</strong>选择 {3,7} 使得结果数组为 [5,5,5,2,2]、长度为 5（原数组长度的一半）。
大小为 2 的可行集合有 {3,5},{3,2},{5,2}。
选择 {2,7} 是不可行的，它的结果数组为 [3,3,3,3,5,5,5]，新数组长度大于原数组的二分之一。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [7,7,7,7,7,7]
<strong>输出：</strong>1
<strong>解释：</strong>我们只能选择集合 {7}，结果数组为空。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>arr.length</code>&nbsp;为偶数</li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 排序

我们可以用哈希表或数组 $cnt$ 统计数组 $\textit{arr}$ 中每个数字出现的次数，然后将 $cnt$ 中的数字从大到小排序，从大到小遍历 $\textit{cnt}$，每次遍历将当前数字 $x$ 加入答案，并将 $m$ 加上 $x$，如果 $m \geq \frac{n}{2}$，则返回答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{arr}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSetSize(self, arr: List[int]) -> int:
        cnt = Counter(arr)
        ans = m = 0
        for _, v in cnt.most_common():
            m += v
            ans += 1
            if m * 2 >= len(arr):
                break
        return ans
```

#### Java

```java
class Solution {
    public int minSetSize(int[] arr) {
        int mx = 0;
        for (int x : arr) {
            mx = Math.max(mx, x);
        }
        int[] cnt = new int[mx + 1];
        for (int x : arr) {
            ++cnt[x];
        }
        Arrays.sort(cnt);
        int ans = 0;
        int m = 0;
        for (int i = mx;; --i) {
            if (cnt[i] > 0) {
                m += cnt[i];
                ++ans;
                if (m * 2 >= arr.length) {
                    return ans;
                }
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSetSize(vector<int>& arr) {
        int mx = ranges::max(arr);
        int cnt[mx + 1];
        memset(cnt, 0, sizeof(cnt));
        for (int& x : arr) {
            ++cnt[x];
        }
        sort(cnt, cnt + mx + 1, greater<int>());
        int ans = 0;
        int m = 0;
        for (int& x : cnt) {
            if (x) {
                m += x;
                ++ans;
                if (m * 2 >= arr.size()) {
                    break;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minSetSize(arr []int) (ans int) {
	mx := slices.Max(arr)
	cnt := make([]int, mx+1)
	for _, x := range arr {
		cnt[x]++
	}
	sort.Ints(cnt)
	for i, m := mx, 0; ; i-- {
		if cnt[i] > 0 {
			m += cnt[i]
			ans++
			if m >= len(arr)/2 {
				return
			}
		}
	}
}
```

#### TypeScript

```ts
function minSetSize(arr: number[]): number {
    const cnt = new Map<number, number>();
    for (const v of arr) {
        cnt.set(v, (cnt.get(v) ?? 0) + 1);
    }
    let [ans, m] = [0, 0];
    for (const v of Array.from(cnt.values()).sort((a, b) => b - a)) {
        m += v;
        ++ans;
        if (m * 2 >= arr.length) {
            break;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
