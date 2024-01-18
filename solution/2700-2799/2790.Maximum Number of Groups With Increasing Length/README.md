# [2790. 长度递增组的最大数目](https://leetcode.cn/problems/maximum-number-of-groups-with-increasing-length)

[English Version](/solution/2700-2799/2790.Maximum%20Number%20of%20Groups%20With%20Increasing%20Length/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始、长度为 <code>n</code> 的数组 <code>usageLimits</code> 。</p>

<p>你的任务是使用从 <code>0</code> 到 <code>n - 1</code> 的数字创建若干组，并确保每个数字 <code>i</code> 在 <strong>所有组</strong> 中使用的次数总共不超过 <code>usageLimits[i]</code> 次。此外，还必须满足以下条件：</p>

<ul>
	<li>每个组必须由 <strong>不同</strong> 的数字组成，也就是说，单个组内不能存在重复的数字。</li>
	<li>每个组（除了第一个）的长度必须 <strong>严格大于</strong> 前一个组。</li>
</ul>

<p>在满足所有条件的情况下，以整数形式返回可以创建的最大组数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<code><strong>输入：</strong>usageLimits</code> = [1,2,5]
<strong>输出：</strong>3
<strong>解释：</strong>在这个示例中，我们可以使用 0 至多一次，使用 1 至多 2 次，使用 2 至多 5 次。
一种既能满足所有条件，又能创建最多组的方式是： 
组 1 包含数字 [2] 。
组 2 包含数字 [1,2] 。
组 3 包含数字 [0,1,2] 。 
可以证明能够创建的最大组数是 3 。 
所以，输出是 3 。 </pre>

<p><strong>示例 2：</strong></p>

<pre>
<code><strong>输入：</strong></code><code>usageLimits</code> = [2,1,2]
<strong>输出：</strong>2
<strong>解释：</strong>在这个示例中，我们可以使用 0 至多 2 次，使用 1 至多 1 次，使用 2 至多 2 次。
一种既能满足所有条件，又能创建最多组的方式是： 
组 1 包含数字 [0] 。 
组 2 包含数字 [1,2] 。
可以证明能够创建的最大组数是 2 。 
所以，输出是 2 。 
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<code><strong>输入：</strong></code><code>usageLimits</code> = [1,1]
<strong>输出：</strong>1
<strong>解释：</strong>在这个示例中，我们可以使用 0 和 1 至多 1 次。 
一种既能满足所有条件，又能创建最多组的方式是：
组 1 包含数字 [0] 。
可以证明能够创建的最大组数是 1 。 
所以，输出是 1 。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= usageLimits.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= usageLimits[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def maxIncreasingGroups(self, usageLimits: List[int]) -> int:
        usageLimits.sort()
        k, n = 0, len(usageLimits)
        for i in range(n):
            if usageLimits[i] > k:
                k += 1
                usageLimits[i] -= k
            if i + 1 < n:
                usageLimits[i + 1] += usageLimits[i]
        return k
```

```java
class Solution {
    public int maxIncreasingGroups(List<Integer> usageLimits) {
        Collections.sort(usageLimits);
        int k = 0;
        long s = 0;
        for (int x : usageLimits) {
            s += x;
            if (s > k) {
                ++k;
                s -= k;
            }
        }
        return k;
    }
}
```

```cpp
class Solution {
public:
    int maxIncreasingGroups(vector<int>& usageLimits) {
        sort(usageLimits.begin(), usageLimits.end());
        int k = 0;
        long long s = 0;
        for (int x : usageLimits) {
            s += x;
            if (s > k) {
                ++k;
                s -= k;
            }
        }
        return k;
    }
};
```

```go
func maxIncreasingGroups(usageLimits []int) int {
	sort.Ints(usageLimits)
	s, k := 0, 0
	for _, x := range usageLimits {
		s += x
		if s > k {
			k++
			s -= k
		}
	}
	return k
}
```

```ts
function maxIncreasingGroups(usageLimits: number[]): number {
    usageLimits.sort((a, b) => a - b);
    let k = 0;
    let s = 0;
    for (const x of usageLimits) {
        s += x;
        if (s > k) {
            ++k;
            s -= k;
        }
    }
    return k;
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def maxIncreasingGroups(self, usageLimits: List[int]) -> int:
        usageLimits.sort()
        k = s = 0
        for x in usageLimits:
            s += x
            if s > k:
                k += 1
                s -= k
        return k
```

<!-- tabs:end -->

<!-- end -->
