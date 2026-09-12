---
comments: true
difficulty: 简单
rating: 1244
source: 第 11 场双周赛 Q1
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [1228. 等差数列中缺失的数字 🔒](https://leetcode.cn/problems/missing-number-in-arithmetic-progression)

[English Version](/solution/1200-1299/1228.Missing%20Number%20In%20Arithmetic%20Progression/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在某个数组&nbsp;<code>arr</code>&nbsp;中，值符合等差数列的数值规律：在&nbsp;<code>0 &lt;= i &lt; arr.length - 1</code>&nbsp;的前提下，<code>arr[i+1] - arr[i]</code>&nbsp;的值都相等。</p>

<p>我们会从该数组中删除一个 <strong>既不是第一个 </strong>也<strong>&nbsp;不是最后一个的值</strong>，得到一个新的数组&nbsp;&nbsp;<code>arr</code>。</p>

<p>给你这个缺值的数组&nbsp;<code>arr</code>，返回 <em>被删除的那个数</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [5,7,11,13]
<strong>输出：</strong>9
<strong>解释：</strong>原来的数组是 [5,7,<strong>9</strong>,11,13]。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [15,13,12]
<strong>输出：</strong>14
<strong>解释：</strong>原来的数组是 [15,<strong>14</strong>,13,12]。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
	<li>给定的数组 <strong>保证</strong> 是一个有效的数组。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：等差数列求和公式

<!-- thinking:start -->

> **思考**
>
> 数组由等差数列删去一项得到，$n \le 1000$。完整数列有 $n+1$ 项，首末项已知，其和可由求和公式写出；与当前数组元素和之差即缺失项。一次求和即可，不必先求公差。

<!-- thinking:end -->

等差数列求和公式为 $\frac{(a_1 + a_n)n}{2}$，其中 $n$ 为等差数列的项数，等差数列的首项为 $a_1$，末项为 $a_n$。

因为题目中给出的数组是一个等差数列，且缺失了一个数，所以数组的项数为 $n + 1$，首项为 $a_1$，末项为 $a_n$，则数组的和为 $\frac{(a_1 + a_n)(n + 1)}{2}$。

因此，缺失的数为 $\frac{(a_1 + a_n)(n + 1)}{2} - \sum_{i = 0}^n a_i$。

时间复杂度 $O(n)$，其中 $n$ 为数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def missingNumber(self, arr: List[int]) -> int:
        return (arr[0] + arr[-1]) * (len(arr) + 1) // 2 - sum(arr)
```

#### Java

```java
class Solution {
    public int missingNumber(int[] arr) {
        int n = arr.length;
        int x = (arr[0] + arr[n - 1]) * (n + 1) / 2;
        int y = Arrays.stream(arr).sum();
        return x - y;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int missingNumber(vector<int>& arr) {
        int n = arr.size();
        int x = (arr[0] + arr[n - 1]) * (n + 1) / 2;
        int y = accumulate(arr.begin(), arr.end(), 0);
        return x - y;
    }
};
```

#### Go

```go
func missingNumber(arr []int) int {
	n := len(arr)
	x := (arr[0] + arr[n-1]) * (n + 1) / 2
	y := 0
	for _, v := range arr {
		y += v
	}
	return x - y
}
```

#### TypeScript

```ts
function missingNumber(arr: number[]): number {
    const x = ((arr[0] + arr.at(-1)!) * (arr.length + 1)) >> 1;
    const y = arr.reduce((acc, cur) => acc + cur, 0);
    return x - y;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：求公差 + 遍历

<!-- thinking:start -->

> **思考**
>
> 求和公式给出缺失值，但不经过公差。若先用首末项与长度求出 $d$，再扫描相邻差不等于 $d$ 的位置，即可在断点处还原缺失项；若全程相等则缺的是与各项相同的数。时间同为线性，过程更贴近数列定义。

<!-- thinking:end -->

因为题目中给出的数组是一个等差数列，且缺失了一个数，首项为 $a_1$，末项为 $a_n$，那么公差 $d = \frac{a_n - a_1}{n}$。

遍历数组，如果 $a_i \neq a_{i - 1} + d$，则返回 $a_{i - 1} + d$。

如果遍历完数组都没有找到缺失的数，说明数组的所有数都相等，直接返回数组的第一个数即可。

时间复杂度 $O(n)$，其中 $n$ 为数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def missingNumber(self, arr: List[int]) -> int:
        n = len(arr)
        d = (arr[-1] - arr[0]) // n
        for i in range(1, n):
            if arr[i] != arr[i - 1] + d:
                return arr[i - 1] + d
        return arr[0]
```

#### Java

```java
class Solution {
    public int missingNumber(int[] arr) {
        int n = arr.length;
        int d = (arr[n - 1] - arr[0]) / n;
        for (int i = 1; i < n; ++i) {
            if (arr[i] != arr[i - 1] + d) {
                return arr[i - 1] + d;
            }
        }
        return arr[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int missingNumber(vector<int>& arr) {
        int n = arr.size();
        int d = (arr[n - 1] - arr[0]) / n;
        for (int i = 1; i < n; ++i) {
            if (arr[i] != arr[i - 1] + d) {
                return arr[i - 1] + d;
            }
        }
        return arr[0];
    }
};
```

#### Go

```go
func missingNumber(arr []int) int {
	n := len(arr)
	d := (arr[n-1] - arr[0]) / n
	for i := 1; i < n; i++ {
		if arr[i] != arr[i-1]+d {
			return arr[i-1] + d
		}
	}
	return arr[0]
}
```

#### TypeScript

```ts
function missingNumber(arr: number[]): number {
    const d = ((arr.at(-1)! - arr[0]) / arr.length) | 0;
    for (let i = 1; i < arr.length; ++i) {
        if (arr[i] - arr[i - 1] !== d) {
            return arr[i - 1] + d;
        }
    }
    return arr[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
