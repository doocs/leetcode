---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1052.Grumpy%20Bookstore%20Owner/README.md
rating: 1418
source: 第 138 场周赛 Q2
tags:
    - 数组
    - 滑动窗口
---

<!-- problem:start -->

# [1052. 爱生气的书店老板](https://leetcode.cn/problems/grumpy-bookstore-owner)

[English Version](/solution/1000-1099/1052.Grumpy%20Bookstore%20Owner/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一个书店老板，他的书店开了&nbsp;<code>n</code>&nbsp;分钟。每分钟都有一些顾客进入这家商店。给定一个长度为 <code>n</code> 的整数数组 <code>customers</code> ，其中 <code>customers[i]</code> 是在第 <code>i</code> 分钟开始时进入商店的顾客数量，所有这些顾客在第 <code>i</code> 分钟结束后离开。</p>

<p>在某些时候，书店老板会生气。 如果书店老板在第 <code>i</code> 分钟生气，那么 <code>grumpy[i] = 1</code>，否则 <code>grumpy[i] = 0</code>。</p>

<p>当书店老板生气时，那一分钟的顾客就会不满意，若老板不生气则顾客是满意的。</p>

<p>书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续&nbsp;<code>minutes</code>&nbsp;分钟不生气，但却只能使用一次。</p>

<p>请你返回 <em>这一天营业下来，最多有多少客户能够感到满意</em> 。<br />
&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
<strong>输出：</strong>16
<strong>解释：</strong>书店老板在最后 3 分钟保持冷静。
感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>customers = [1], grumpy = [0], minutes = 1
<strong>输出：</strong>1</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == customers.length == grumpy.length</code></li>
	<li><code>1 &lt;= minutes &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= customers[i] &lt;= 1000</code></li>
	<li><code>grumpy[i] == 0 or 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口

根据题目描述，我们只需要统计老板不生气时的客户数量 $tot$，再加上一个大小为 `minutes` 的滑动窗口中，老板生气时的客户数量的最大值 $mx$ 即可。

我们定义一个变量 $cnt$ 来记录滑动窗口中老板生气时的客户数量，初始值为前 `minutes` 分钟老板生气时的客户数量。然后我们遍历数组，每次移动滑动窗口时，更新 $cnt$ 的值，同时更新 $mx$ 的值。

最后返回 $tot + mx$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 `customers` 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def maxSatisfied(
        self, customers: List[int], grumpy: List[int], minutes: int
    ) -> int:
        mx = cnt = sum(c * g for c, g in zip(customers[:minutes], grumpy))
        for i in range(minutes, len(customers)):
            cnt += customers[i] * grumpy[i]
            cnt -= customers[i - minutes] * grumpy[i - minutes]
            mx = max(mx, cnt)
        return sum(c * (g ^ 1) for c, g in zip(customers, grumpy)) + mx
```

```java
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int cnt = 0;
        int tot = 0;
        for (int i = 0; i < minutes; ++i) {
            cnt += customers[i] * grumpy[i];
            tot += customers[i] * (grumpy[i] ^ 1);
        }
        int mx = cnt;
        int n = customers.length;
        for (int i = minutes; i < n; ++i) {
            cnt += customers[i] * grumpy[i];
            cnt -= customers[i - minutes] * grumpy[i - minutes];
            mx = Math.max(mx, cnt);
            tot += customers[i] * (grumpy[i] ^ 1);
        }
        return tot + mx;
    }
}
```

```cpp
class Solution {
public:
    int maxSatisfied(vector<int>& customers, vector<int>& grumpy, int minutes) {
        int cnt = 0;
        int tot = 0;
        for (int i = 0; i < minutes; ++i) {
            cnt += customers[i] * grumpy[i];
            tot += customers[i] * (grumpy[i] ^ 1);
        }
        int mx = cnt;
        int n = customers.size();
        for (int i = minutes; i < n; ++i) {
            cnt += customers[i] * grumpy[i];
            cnt -= customers[i - minutes] * grumpy[i - minutes];
            mx = max(mx, cnt);
            tot += customers[i] * (grumpy[i] ^ 1);
        }
        return tot + mx;
    }
};
```

```go
func maxSatisfied(customers []int, grumpy []int, minutes int) int {
	var cnt, tot int
	for i, c := range customers[:minutes] {
		cnt += c * grumpy[i]
		tot += c * (grumpy[i] ^ 1)
	}
	mx := cnt
	for i := minutes; i < len(customers); i++ {
		cnt += customers[i] * grumpy[i]
		cnt -= customers[i-minutes] * grumpy[i-minutes]
		mx = max(mx, cnt)
		tot += customers[i] * (grumpy[i] ^ 1)
	}
	return tot + mx
}
```

```ts
function maxSatisfied(customers: number[], grumpy: number[], minutes: number): number {
    let [cnt, tot] = [0, 0];
    for (let i = 0; i < minutes; ++i) {
        cnt += customers[i] * grumpy[i];
        tot += customers[i] * (grumpy[i] ^ 1);
    }
    let mx = cnt;
    for (let i = minutes; i < customers.length; ++i) {
        cnt += customers[i] * grumpy[i];
        cnt -= customers[i - minutes] * grumpy[i - minutes];
        mx = Math.max(mx, cnt);
        tot += customers[i] * (grumpy[i] ^ 1);
    }
    return tot + mx;
}
```

```rust
impl Solution {
    pub fn max_satisfied(customers: Vec<i32>, grumpy: Vec<i32>, minutes: i32) -> i32 {
        let mut cnt = 0;
        let mut tot = 0;
        let minutes = minutes as usize;
        for i in 0..minutes {
            cnt += customers[i] * grumpy[i];
            tot += customers[i] * (1 - grumpy[i]);
        }
        let mut mx = cnt;
        let n = customers.len();
        for i in minutes..n {
            cnt += customers[i] * grumpy[i];
            cnt -= customers[i - minutes] * grumpy[i - minutes];
            mx = mx.max(cnt);
            tot += customers[i] * (1 - grumpy[i]);
        }
        tot + mx
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
