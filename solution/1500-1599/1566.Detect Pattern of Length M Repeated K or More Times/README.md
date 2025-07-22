---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1566.Detect%20Pattern%20of%20Length%20M%20Repeated%20K%20or%20More%20Times/README.md
rating: 1486
source: 第 204 场周赛 Q1
tags:
    - 数组
    - 枚举
---

<!-- problem:start -->

# [1566. 重复至少 K 次且长度为 M 的模式](https://leetcode.cn/problems/detect-pattern-of-length-m-repeated-k-or-more-times)

[English Version](/solution/1500-1599/1566.Detect%20Pattern%20of%20Length%20M%20Repeated%20K%20or%20More%20Times/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数数组 <code>arr</code>，请你找出一个长度为 <code>m</code> 且在数组中至少重复 <code>k</code> 次的模式。</p>

<p><strong>模式</strong> 是由一个或多个值组成的子数组（连续的子序列），<strong>连续</strong> 重复多次但 <strong>不重叠</strong> 。 模式由其长度和重复次数定义。</p>

<p>如果数组中存在至少重复 <code>k</code> 次且长度为 <code>m</code> 的模式，则返回 <code>true</code> ，否则返回&nbsp; <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,4,4,4,4], m = 1, k = 3
<strong>输出：</strong>true
<strong>解释：</strong>模式 <strong>(4)</strong> 的长度为 1 ，且连续重复 4 次。注意，模式可以重复 k 次或更多次，但不能少于 k 次。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,1,2,1,1,1,3], m = 2, k = 2
<strong>输出：</strong>true
<strong>解释：</strong>模式 <strong>(1,2)</strong> 长度为 2 ，且连续重复 2 次。另一个符合题意的模式是 <strong>(2,1) </strong>，同样重复 2 次。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,1,2,1,3], m = 2, k = 3
<strong>输出：</strong>false
<strong>解释：</strong>模式 <strong>(1,2)</strong> 长度为 2 ，但是只连续重复 2 次。不存在长度为 2 且至少重复 3 次的模式。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,3,1,2], m = 2, k = 2
<strong>输出：</strong>false
<strong>解释：</strong>模式 <strong>(1,2)</strong> 出现 2 次但并不连续，所以不能算作连续重复 2 次。
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>arr = [2,2,2,2], m = 2, k = 3
<strong>输出：</strong>false
<strong>解释：</strong>长度为 2 的模式只有 <strong>(2,2)</strong> ，但是只连续重复 2 次。注意，不能计算重叠的重复次数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 100</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 100</code></li>
	<li><code>1 &lt;= m&nbsp;&lt;= 100</code></li>
	<li><code>2 &lt;= k&nbsp;&lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

首先，如果数组的长度小于 $m \times k$，那么肯定不存在长度为 $m$ 且至少重复 $k$ 次的模式，直接返回 $\textit{false}$。

接下来，我们定义一个变量 $\textit{cnt}$ 来记录当前连续重复的次数，如果数组存在连续的 $(k - 1) \times m$ 个元素 $a_i$，使得 $a_i = a_{i - m}$，那么我们就找到了一个长度为 $m$ 且至少重复 $k$ 次的模式，返回 $\textit{true}$。否则，我们将 $\textit{cnt}$ 置为 $0$，继续遍历数组。

最后，如果遍历完数组都没有找到符合条件的模式，返回 $\textit{false}$。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def containsPattern(self, arr: List[int], m: int, k: int) -> bool:
        if len(arr) < m * k:
            return False
        cnt, target = 0, (k - 1) * m
        for i in range(m, len(arr)):
            if arr[i] == arr[i - m]:
                cnt += 1
                if cnt == target:
                    return True
            else:
                cnt = 0
        return False
```

#### Java

```java
class Solution {
    public boolean containsPattern(int[] arr, int m, int k) {
        if (arr.length < m * k) {
            return false;
        }
        int cnt = 0, target = (k - 1) * m;
        for (int i = m; i < arr.length; ++i) {
            if (arr[i] == arr[i - m]) {
                if (++cnt == target) {
                    return true;
                }
            } else {
                cnt = 0;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool containsPattern(vector<int>& arr, int m, int k) {
        if (arr.size() < m * k) {
            return false;
        }
        int cnt = 0, target = (k - 1) * m;
        for (int i = m; i < arr.size(); ++i) {
            if (arr[i] == arr[i - m]) {
                if (++cnt == target) {
                    return true;
                }
            } else {
                cnt = 0;
            }
        }
        return false;
    }
};
```

#### Go

```go
func containsPattern(arr []int, m int, k int) bool {
	cnt, target := 0, (k-1)*m
	for i := m; i < len(arr); i++ {
		if arr[i] == arr[i-m] {
			cnt++
			if cnt == target {
				return true
			}
		} else {
			cnt = 0
		}
	}
	return false
}
```

#### TypeScript

```ts
function containsPattern(arr: number[], m: number, k: number): boolean {
    if (arr.length < m * k) {
        return false;
    }
    const target = (k - 1) * m;
    let cnt = 0;
    for (let i = m; i < arr.length; ++i) {
        if (arr[i] === arr[i - m]) {
            if (++cnt === target) {
                return true;
            }
        } else {
            cnt = 0;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
