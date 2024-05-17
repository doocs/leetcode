---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2086.Minimum%20Number%20of%20Food%20Buckets%20to%20Feed%20the%20Hamsters/README.md
rating: 1622
source: 第 66 场双周赛 Q2
tags:
    - 贪心
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [2086. 喂食仓鼠的最小食物桶数](https://leetcode.cn/problems/minimum-number-of-food-buckets-to-feed-the-hamsters)

[English Version](/solution/2000-2099/2086.Minimum%20Number%20of%20Food%20Buckets%20to%20Feed%20the%20Hamsters/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>hamsters</code>&nbsp;，其中&nbsp;<code>hamsters[i]</code>&nbsp; 要么是：</p>

<ul>
	<li><code>'H'</code>&nbsp;表示有一个仓鼠在下标&nbsp;<code>i</code>&nbsp;，或者</li>
	<li><code>'.'</code>&nbsp;表示下标&nbsp;<code>i</code>&nbsp;是空的。</li>
</ul>

<p>你将要在空的位置上添加一定数量的食物桶来喂养仓鼠。如果仓鼠的左边或右边至少有一个食物桶，就可以喂食它。更正式地说，如果你在位置&nbsp;<code>i - 1</code>&nbsp;<strong>或者</strong> <code>i + 1</code>&nbsp;放置一个食物桶，就可以喂养位置为 <code>i</code>&nbsp;处的仓鼠。</p>

<p>在 <strong>空的位置</strong> 放置食物桶以喂养所有仓鼠的前提下，请你返回需要的 <strong>最少</strong>&nbsp;食物桶数。如果无解请返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2086.Minimum%20Number%20of%20Food%20Buckets%20to%20Feed%20the%20Hamsters/images/1710141378-bfEGUX-image.png" style="width: 482px; height: 162px;" /></strong></p>

<pre>
<b>输入：</b>hamsters = "H..H"
<b>输出：</b>2
<strong>解释：</strong>
我们可以在下标为 1 和 2 处放食物桶。
可以发现如果我们只放置 1 个食物桶，其中一只仓鼠将得不到喂养。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2086.Minimum%20Number%20of%20Food%20Buckets%20to%20Feed%20the%20Hamsters/images/1710141384-oLAScv-image.png" style="width: 602px; height: 162px;" /></strong></p>

<pre>
<b>输入：</b>street = ".H.H."
<b>输出：</b>1
<strong>解释：</strong>
我们可以在下标为 2 处放置一个食物桶。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>street = ".HHH."
<b>输出：</b>-1
<strong>解释：</strong>
如果我们如图那样在每个空位放置食物桶，下标 2 处的仓鼠将吃不到食物。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= hamsters.length &lt;= 10<sup>5</sup></code></li>
	<li><code>hamsters[i]</code>&nbsp;要么是&nbsp;<code>'H'</code>&nbsp;，要么是&nbsp;<code>'.'</code> 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

从左到右遍历字符串，遇到 `H` 时，优先考虑右边是否有空位，如果有则放置水桶，并且跳过水桶的下一个位置；如果右边没有空位，则考虑左边是否有空位，如果有则放置水桶，否则无解。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 `street` 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minimumBuckets(self, street: str) -> int:
        ans = 0
        i, n = 0, len(street)
        while i < n:
            if street[i] == 'H':
                if i + 1 < n and street[i + 1] == '.':
                    i += 2
                    ans += 1
                elif i and street[i - 1] == '.':
                    ans += 1
                else:
                    return -1
            i += 1
        return ans
```

```java
class Solution {
    public int minimumBuckets(String street) {
        int n = street.length();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (street.charAt(i) == 'H') {
                if (i + 1 < n && street.charAt(i + 1) == '.') {
                    ++ans;
                    i += 2;
                } else if (i > 0 && street.charAt(i - 1) == '.') {
                    ++ans;
                } else {
                    return -1;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumBuckets(string street) {
        int n = street.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (street[i] == 'H') {
                if (i + 1 < n && street[i + 1] == '.') {
                    ++ans;
                    i += 2;
                } else if (i && street[i - 1] == '.') {
                    ++ans;
                } else {
                    return -1;
                }
            }
        }
        return ans;
    }
};
```

```go
func minimumBuckets(street string) int {
	ans, n := 0, len(street)
	for i := 0; i < n; i++ {
		if street[i] == 'H' {
			if i+1 < n && street[i+1] == '.' {
				ans++
				i += 2
			} else if i > 0 && street[i-1] == '.' {
				ans++
			} else {
				return -1
			}
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
