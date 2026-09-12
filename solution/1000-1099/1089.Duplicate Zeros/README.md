---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1089.Duplicate%20Zeros/README.md
rating: 1262
source: 第 141 场周赛 Q1
tags:
    - 数组
    - 双指针
---

<!-- problem:start -->

# [1089. 复写零](https://leetcode.cn/problems/duplicate-zeros)

[English Version](/solution/1000-1099/1089.Duplicate%20Zeros/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度固定的整数数组&nbsp;<code>arr</code> ，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。</p>

<p>注意：请不要在超过该数组长度的位置写入元素。请对输入的数组&nbsp;<strong>就地&nbsp;</strong>进行上述修改，不要从函数返回任何东西。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,0,2,3,0,4,5,0]
<strong>输出：</strong>[1,0,0,2,3,0,0,4]
<strong>解释：</strong>调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3]
<strong>输出：</strong>[1,2,3]
<strong>解释：</strong>调用函数后，输入的数组将被修改为：[1,2,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

<!-- thinking:start -->

> **思考**
>
> 要求原地把每个 $0$ 复写成两个，超出原长度的部分丢弃。$n\le 10^4$，另开数组再拷回不符合原地。从左写入会覆盖尚未处理的元素，应先算能留下的最右原下标，再从右回填。
>
> 指针 $i$ 与虚拟长度 $k$ 同步前进：非零 $k$ 加 $1$，零加 $2$，直到 $k\ge n$。若最后一次因零使 $k=n+1$，该零只能写一次，先放在末尾。
>
> 然后 $j$ 从 $n-1$ 回写：零连续写两格，非零写一格。

<!-- thinking:end -->

先从左往右扫一遍，统计复写零之后会占到的长度。指针 $i$ 指向原数组中还能留下的最后一个位置，$k$ 是复写后的虚拟长度：非零元素 $k$ 加 $1$，零则加 $2$。当 $k \ge n$ 时停止。

再设写入位置 $j = n - 1$。如果最后一个留下的是零且刚好超出数组末尾（$k = n + 1$），这个零只能写一次，先把它放到 $arr[j]$，再把 $i$ 和 $j$ 各减 $1$。

然后从右往左回填。若 $arr[i] \ne 0$，直接写入 $arr[j]$；若为 $0$，连续写入两个 $0$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def duplicateZeros(self, arr: List[int]) -> None:
        """
        Do not return anything, modify arr in-place instead.
        """
        n = len(arr)
        i, k = -1, 0
        while k < n:
            i += 1
            k += 1 if arr[i] else 2
        j = n - 1
        if k == n + 1:
            arr[j] = 0
            i, j = i - 1, j - 1
        while ~j:
            if arr[i] == 0:
                arr[j] = arr[j - 1] = arr[i]
                j -= 1
            else:
                arr[j] = arr[i]
            i, j = i - 1, j - 1
```

#### Java

```java
class Solution {
    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int i = -1, k = 0;
        while (k < n) {
            ++i;
            k += arr[i] > 0 ? 1 : 2;
        }
        int j = n - 1;
        if (k == n + 1) {
            arr[j--] = 0;
            --i;
        }
        while (j >= 0) {
            arr[j] = arr[i];
            if (arr[i] == 0) {
                arr[--j] = arr[i];
            }
            --i;
            --j;
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    void duplicateZeros(vector<int>& arr) {
        int n = arr.size();
        int i = -1, k = 0;
        while (k < n) {
            ++i;
            k += arr[i] ? 1 : 2;
        }
        int j = n - 1;
        if (k == n + 1) {
            arr[j--] = 0;
            --i;
        }
        while (~j) {
            arr[j] = arr[i];
            if (arr[i] == 0) arr[--j] = arr[i];
            --i;
            --j;
        }
    }
};
```

#### Go

```go
func duplicateZeros(arr []int) {
	n := len(arr)
	i, k := -1, 0
	for k < n {
		i, k = i+1, k+1
		if arr[i] == 0 {
			k++
		}
	}
	j := n - 1
	if k == n+1 {
		arr[j] = 0
		i, j = i-1, j-1
	}
	for j >= 0 {
		arr[j] = arr[i]
		if arr[i] == 0 {
			j--
			arr[j] = arr[i]
		}
		i, j = i-1, j-1
	}
}
```

#### Rust

```rust
impl Solution {
    pub fn duplicate_zeros(arr: &mut Vec<i32>) {
        let n = arr.len();
        let mut i = 0;
        let mut j = 0;
        while j < n {
            if arr[i] == 0 {
                j += 1;
            }
            j += 1;
            i += 1;
        }
        while i > 0 {
            if arr[i - 1] == 0 {
                if j <= n {
                    arr[j - 1] = arr[i - 1];
                }
                j -= 1;
            }
            arr[j - 1] = arr[i - 1];
            i -= 1;
            j -= 1;
        }
    }
}
```

#### C

```c
void duplicateZeros(int* arr, int arrSize) {
    int i = 0;
    int j = 0;
    while (j < arrSize) {
        if (arr[i] == 0) {
            j++;
        }
        i++;
        j++;
    }
    i--;
    j--;
    while (i >= 0) {
        if (arr[i] == 0) {
            if (j < arrSize) {
                arr[j] = arr[i];
            }
            j--;
        }
        arr[j] = arr[i];
        i--;
        j--;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
