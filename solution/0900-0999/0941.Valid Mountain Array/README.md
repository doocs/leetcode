---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0941.Valid%20Mountain%20Array/README.md
tags:
    - 数组
---

# [941. 有效的山脉数组](https://leetcode.cn/problems/valid-mountain-array)

[English Version](/solution/0900-0999/0941.Valid%20Mountain%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 <code>arr</code>，如果它是有效的山脉数组就返回&nbsp;<code>true</code>，否则返回 <code>false</code>。</p>

<p>让我们回顾一下，如果 <code>arr</code>&nbsp;满足下述条件，那么它是一个山脉数组：</p>

<ul>
	<li><code>arr.length &gt;= 3</code></li>
	<li>在&nbsp;<code>0 &lt; i&nbsp;&lt; arr.length - 1</code>&nbsp;条件下，存在&nbsp;<code>i</code>&nbsp;使得：
	<ul>
		<li><code>arr[0] &lt; arr[1] &lt; ... arr[i-1] &lt; arr[i] </code></li>
		<li><code>arr[i] &gt; arr[i+1] &gt; ... &gt; arr[arr.length - 1]</code></li>
	</ul>
	</li>
</ul>

<p>&nbsp;</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0941.Valid%20Mountain%20Array/images/hint_valid_mountain_array.png" style="height: 316px; width: 500px;" /></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [2,1]
<strong>输出：</strong>false
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [3,5,5]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,3,2,1]
<strong>输出：</strong>true</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

### 方法一：双指针

我们首先判断数组的长度是否小于 $3$，如果小于 $3$，那么一定不是山脉数组，直接返回 `false`。

然后，我们使用指针 $i$ 从数组的左端开始向右移动，直到找到一个位置 $i$，使得 $arr[i] > arr[i + 1]$。然后，我们使用指针 $j$ 从数组的右端开始向左移动，直到找到一个位置 $j$，使得 $arr[j] > arr[j - 1]$。如果满足条件 $i = j$，那么就说明数组 $arr$ 是一个山脉数组。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def validMountainArray(self, arr: List[int]) -> bool:
        n = len(arr)
        if n < 3:
            return False
        i, j = 0, n - 1
        while i + 1 < n - 1 and arr[i] < arr[i + 1]:
            i += 1
        while j - 1 > 0 and arr[j - 1] > arr[j]:
            j -= 1
        return i == j
```

```java
class Solution {
    public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        if (n < 3) {
            return false;
        }
        int i = 0, j = n - 1;
        while (i + 1 < n - 1 && arr[i] < arr[i + 1]) {
            ++i;
        }
        while (j - 1 > 0 && arr[j - 1] > arr[j]) {
            --j;
        }
        return i == j;
    }
}
```

```cpp
class Solution {
public:
    bool validMountainArray(vector<int>& arr) {
        int n = arr.size();
        if (n < 3) {
            return false;
        }
        int i = 0, j = n - 1;
        while (i + 1 < n - 1 && arr[i] < arr[i + 1]) {
            ++i;
        }
        while (j - 1 > 0 && arr[j - 1] > arr[j]) {
            --j;
        }
        return i == j;
    }
};
```

```go
func validMountainArray(arr []int) bool {
	n := len(arr)
	if n < 3 {
		return false
	}
	i, j := 0, n-1
	for i+1 < n-1 && arr[i] < arr[i+1] {
		i++
	}
	for j-1 > 0 && arr[j-1] > arr[j] {
		j--
	}
	return i == j
}
```

```ts
function validMountainArray(arr: number[]): boolean {
    const n = arr.length;
    if (n < 3) {
        return false;
    }
    let [i, j] = [0, n - 1];
    while (i + 1 < n - 1 && arr[i] < arr[i + 1]) {
        i++;
    }
    while (j - 1 > 0 && arr[j] < arr[j - 1]) {
        j--;
    }
    return i === j;
}
```

<!-- tabs:end -->

<!-- end -->
