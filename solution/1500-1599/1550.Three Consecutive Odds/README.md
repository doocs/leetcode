---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1550.Three%20Consecutive%20Odds/README.md
rating: 1221
source: 第 202 场周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [1550. 存在连续三个奇数的数组](https://leetcode.cn/problems/three-consecutive-odds)

[English Version](/solution/1500-1599/1550.Three%20Consecutive%20Odds/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>arr</code>，请你判断数组中是否存在连续三个元素都是奇数的情况：如果存在，请返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [2,6,4,1]
<strong>输出：</strong>false
<strong>解释：</strong>不存在连续三个元素都是奇数的情况。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,34,3,4,5,7,23,12]
<strong>输出：</strong>true
<strong>解释：</strong>存在连续三个元素都是奇数的情况，即 [5,7,23] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历 + 计数

我们用一个变量 $\textit{cnt}$ 记录当前连续奇数的个数。

接下来，我们遍历数组，如果当前元素是奇数，则 $\textit{cnt}$ 加一，如果 $\textit{cnt}$ 等于 3，则返回 $\textit{True}$。如果当前元素是偶数，则 $\textit{cnt}$ 置零。

遍历结束后，如果没有找到连续三个奇数，则返回 $\textit{False}$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{arr}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def threeConsecutiveOdds(self, arr: List[int]) -> bool:
        cnt = 0
        for x in arr:
            if x & 1:
                cnt += 1
                if cnt == 3:
                    return True
            else:
                cnt = 0
        return False
```

#### Java

```java
class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int cnt = 0;
        for (int x : arr) {
            if (x % 2 == 1) {
                if (++cnt == 3) {
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
    bool threeConsecutiveOdds(vector<int>& arr) {
        int cnt = 0;
        for (int x : arr) {
            if (x & 1) {
                if (++cnt == 3) {
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
func threeConsecutiveOdds(arr []int) bool {
	cnt := 0
	for _, x := range arr {
		if x&1 == 1 {
			cnt++
			if cnt == 3 {
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
function threeConsecutiveOdds(arr: number[]): boolean {
    let cnt = 0;
    for (const x of arr) {
        if (x & 1) {
            if (++cnt == 3) {
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

<!-- solution:start -->

### 方法二：遍历 + 位运算

根据位运算的性质，两个数进行按位与运算是奇数，当且仅当两个数都是奇数。如果有连续三个数按位与运算的结果是奇数，那么这三个数都是奇数。

因此，我们只需要遍历数组，判断是否存在连续三个数的按位与结果是否是奇数即可，如果存在则返回 $\textit{True}$，否则返回 $\textit{False}$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{arr}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def threeConsecutiveOdds(self, arr: List[int]) -> bool:
        return any(x & arr[i + 1] & arr[i + 2] & 1 for i, x in enumerate(arr[:-2]))
```

#### Java

```java
class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        for (int i = 2, n = arr.length; i < n; ++i) {
            if ((arr[i - 2] & arr[i - 1] & arr[i] & 1) == 1) {
                return true;
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
    bool threeConsecutiveOdds(vector<int>& arr) {
        for (int i = 2, n = arr.size(); i < n; ++i) {
            if (arr[i - 2] & arr[i - 1] & arr[i] & 1) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func threeConsecutiveOdds(arr []int) bool {
	for i, n := 2, len(arr); i < n; i++ {
		if arr[i-2]&arr[i-1]&arr[i]&1 == 1 {
			return true
		}
	}
	return false
}
```

#### TypeScript

```ts
function threeConsecutiveOdds(arr: number[]): boolean {
    const n = arr.length;
    for (let i = 2; i < n; ++i) {
        if (arr[i - 2] & arr[i - 1] & arr[i] & 1) {
            return true;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
