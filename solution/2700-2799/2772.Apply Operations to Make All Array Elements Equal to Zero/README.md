---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2772.Apply%20Operations%20to%20Make%20All%20Array%20Elements%20Equal%20to%20Zero/README.md
rating: 2029
source: 第 353 场周赛 Q4
tags:
    - 数组
    - 前缀和
---

<!-- problem:start -->

# [2772. 使数组中的所有元素都等于零](https://leetcode.cn/problems/apply-operations-to-make-all-array-elements-equal-to-zero)

[English Version](/solution/2700-2799/2772.Apply%20Operations%20to%20Make%20All%20Array%20Elements%20Equal%20to%20Zero/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 和一个正整数 <code>k</code> 。</p>

<p>你可以对数组执行下述操作 <strong>任意次</strong> ：</p>

<ul>
	<li>从数组中选出长度为 <code>k</code> 的 <strong>任一</strong> 子数组，并将子数组中每个元素都 <strong>减去</strong> <code>1</code> 。</li>
</ul>

<p>如果你可以使数组中的所有元素都等于 <code>0</code> ，返回&nbsp; <code>true</code><em> </em>；否则，返回<em> </em><code>false</code><em> </em>。</p>

<p><strong>子数组</strong> 是数组中的一个非空连续元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [2,2,3,1,1,0], k = 3
<strong>输出：</strong>true
<strong>解释：</strong>可以执行下述操作：
- 选出子数组 [2,2,3] ，执行操作后，数组变为 nums = [<em><strong>1</strong></em>,<em><strong>1</strong></em>,<em><strong>2</strong></em>,1,1,0] 。
- 选出子数组 [2,1,1] ，执行操作后，数组变为 nums = [1,1,<em><strong>1</strong></em>,<em><strong>0</strong></em>,<em><strong>0</strong></em>,0] 。
- 选出子数组 [1,1,1] ，执行操作后，数组变为 nums = [<em><strong>0</strong></em>,<em><strong>0</strong></em>,<em><strong>0</strong></em>,0,0,0] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,3,1,1], k = 2
<strong>输出：</strong>false
<strong>解释：</strong>无法使数组中的所有元素等于 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：差分数组 + 前缀和

我们先考虑 $nums$ 的第一个元素 $nums[0]$：

-   如果 $nums[0] = 0$，那么我们可以不用操作；
-   如果 $nums[0] \gt 0$，那么我们需要对 $nums[0..k-1]$ 操作 $nums[0]$ 次，使得 $nums[0..k-1]$ 中的元素都减去 $nums[0]$，这样 $nums[0]$ 就变成了 $0$。

对一段连续的元素同时进行加减操作，我们可以使用差分数组来维护这些操作，我们用 $d[i]$ 表示差分数组，对差分数组求前缀和，就可以得到每个位置的数值的变化量。

因此，我们遍历 $nums$，对于每个元素 $nums[i]$，当前位置的变化量 $s = \sum_{j=0}^{i} d[j]$，我们将 $nums[i]$ 加上 $s$，就得到了当前 $nums[i]$ 的实际值。

-   如果 $nums[i] = 0$，那么无须进行操作，直接跳过。
-   如果 $nums[i]=0$，或者 $i + k \gt n$，说明经过前面的操作，$nums[i]$ 已经变成了负数，或者 $nums[i..i+k-1]$ 越界，那么无法使得 $nums$ 中的所有元素都等于 $0$，返回 `false`。否则，我们需要将 $[i..i+k-1]$ 这段区间的所有元素都减去 $nums[i]$，因此我们将 $s$ 减去 $nums[i]$，并将 $d[i+k]$ 加上 $nums[i]$。
-   继续遍历下一个元素。

遍历结束，说明可以使得 $nums$ 中的所有元素都等于 $0$，返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkArray(self, nums: List[int], k: int) -> bool:
        n = len(nums)
        d = [0] * (n + 1)
        s = 0
        for i, x in enumerate(nums):
            s += d[i]
            x += s
            if x == 0:
                continue
            if x < 0 or i + k > n:
                return False
            s -= x
            d[i + k] += x
        return True
```

#### Java

```java
class Solution {
    public boolean checkArray(int[] nums, int k) {
        int n = nums.length;
        int[] d = new int[n + 1];
        int s = 0;
        for (int i = 0; i < n; ++i) {
            s += d[i];
            nums[i] += s;
            if (nums[i] == 0) {
                continue;
            }
            if (nums[i] < 0 || i + k > n) {
                return false;
            }
            s -= nums[i];
            d[i + k] += nums[i];
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkArray(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> d(n + 1);
        int s = 0;
        for (int i = 0; i < n; ++i) {
            s += d[i];
            nums[i] += s;
            if (nums[i] == 0) {
                continue;
            }
            if (nums[i] < 0 || i + k > n) {
                return false;
            }
            s -= nums[i];
            d[i + k] += nums[i];
        }
        return true;
    }
};
```

#### Go

```go
func checkArray(nums []int, k int) bool {
	n := len(nums)
	d := make([]int, n+1)
	s := 0
	for i, x := range nums {
		s += d[i]
		x += s
		if x == 0 {
			continue
		}
		if x < 0 || i+k > n {
			return false
		}
		s -= x
		d[i+k] += x
	}
	return true
}
```

#### TypeScript

```ts
function checkArray(nums: number[], k: number): boolean {
    const n = nums.length;
    const d: number[] = Array(n + 1).fill(0);
    let s = 0;
    for (let i = 0; i < n; ++i) {
        s += d[i];
        nums[i] += s;
        if (nums[i] === 0) {
            continue;
        }
        if (nums[i] < 0 || i + k > n) {
            return false;
        }
        s -= nums[i];
        d[i + k] += nums[i];
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
