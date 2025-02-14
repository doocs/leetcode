---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1534.Count%20Good%20Triplets/README.md
rating: 1279
source: 第 200 场周赛 Q1
tags:
    - 数组
    - 枚举
---

<!-- problem:start -->

# [1534. 统计好三元组](https://leetcode.cn/problems/count-good-triplets)

[English Version](/solution/1500-1599/1534.Count%20Good%20Triplets/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>arr</code> ，以及 <code>a</code>、<code>b</code> 、<code>c</code> 三个整数。请你统计其中好三元组的数量。</p>

<p>如果三元组 <code>(arr[i], arr[j], arr[k])</code> 满足下列全部条件，则认为它是一个 <strong>好三元组</strong> 。</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; k &lt;&nbsp;arr.length</code></li>
	<li><code>|arr[i] - arr[j]| &lt;= a</code></li>
	<li><code>|arr[j] - arr[k]| &lt;= b</code></li>
	<li><code>|arr[i] - arr[k]| &lt;= c</code></li>
</ul>

<p>其中 <code>|x|</code> 表示 <code>x</code> 的绝对值。</p>

<p>返回 <strong>好三元组的数量</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3
<strong>输出：</strong>4
<strong>解释：</strong>一共有 4 个好三元组：[(3,0,1), (3,0,1), (3,1,1), (0,1,1)] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [1,1,2,2,3], a = 0, b = 0, c = 1
<strong>输出：</strong>0
<strong>解释：</strong>不存在满足所有条件的三元组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= arr.length &lt;= 100</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 1000</code></li>
	<li><code>0 &lt;= a, b, c &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以枚举所有的 $i$, $j$ 和 $k$，其中 $i \lt j \lt k$，判断是否同时满足 $|\textit{arr}[i] - \textit{arr}[j]| \le a$，$|\textit{arr}[j] - \textit{arr}[k]| \le b$ 和 $|\textit{arr}[i] - \textit{arr}[k]| \le c$，如果满足则将答案加一。

枚举结束后，即可得到答案。

时间复杂度 $O(n^3)$，其中 $n$ 为数组 $\textit{arr}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countGoodTriplets(self, arr: List[int], a: int, b: int, c: int) -> int:
        ans, n = 0, len(arr)
        for i in range(n):
            for j in range(i + 1, n):
                for k in range(j + 1, n):
                    ans += (
                        abs(arr[i] - arr[j]) <= a
                        and abs(arr[j] - arr[k]) <= b
                        and abs(arr[i] - arr[k]) <= c
                    )
        return ans
```

#### Java

```java
class Solution {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j + 1; k < n; ++k) {
                    if (Math.abs(arr[i] - arr[j]) <= a && Math.abs(arr[j] - arr[k]) <= b
                        && Math.abs(arr[i] - arr[k]) <= c) {
                        ++ans;
                    }
                }
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
    int countGoodTriplets(vector<int>& arr, int a, int b, int c) {
        int n = arr.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j + 1; k < n; ++k) {
                    ans += abs(arr[i] - arr[j]) <= a && abs(arr[j] - arr[k]) <= b && abs(arr[i] - arr[k]) <= c;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countGoodTriplets(arr []int, a int, b int, c int) (ans int) {
	n := len(arr)
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			for k := j + 1; k < n; k++ {
				if abs(arr[i]-arr[j]) <= a && abs(arr[j]-arr[k]) <= b && abs(arr[i]-arr[k]) <= c {
					ans++
				}
			}
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function countGoodTriplets(arr: number[], a: number, b: number, c: number): number {
    let n = arr.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        for (let j = i + 1; j < n; ++j) {
            for (let k = j + 1; k < n; ++k) {
                if (
                    Math.abs(arr[i] - arr[j]) <= a &&
                    Math.abs(arr[j] - arr[k]) <= b &&
                    Math.abs(arr[i] - arr[k]) <= c
                ) {
                    ++ans;
                }
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
