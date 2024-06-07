---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0852.Peak%20Index%20in%20a%20Mountain%20Array/README.md
tags:
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [852. 山脉数组的峰顶索引](https://leetcode.cn/problems/peak-index-in-a-mountain-array)

[English Version](/solution/0800-0899/0852.Peak%20Index%20in%20a%20Mountain%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

符合下列属性的数组 <code>arr</code> 称为 <strong>山脉数组</strong> ：

<ul>
	<li><code>arr.length &gt;= 3</code></li>
	<li>存在 <code>i</code>（<code>0 &lt; i&nbsp;&lt; arr.length - 1</code>）使得：
	<ul>
		<li><code>arr[0] &lt; arr[1] &lt; ... arr[i-1] &lt; arr[i] </code></li>
		<li><code>arr[i] &gt; arr[i+1] &gt; ... &gt; arr[arr.length - 1]</code></li>
	</ul>
	</li>
</ul>

<p>给你由整数组成的山脉数组 <code>arr</code> ，返回满足 <code>arr[0] &lt; arr[1] &lt; ... arr[i - 1] &lt; arr[i] &gt; arr[i + 1] &gt; ... &gt; arr[arr.length - 1]</code> 的下标 <code>i</code> 。</p>

<p>你必须设计并实现时间复杂度为 <code>O(log(n))</code> 的解决方案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,1,0]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,2,1,0]
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [0,10,5,2]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>6</sup></code></li>
	<li>题目数据保证 <code>arr</code> 是一个山脉数组</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def peakIndexInMountainArray(self, arr: List[int]) -> int:
        left, right = 1, len(arr) - 2
        while left < right:
            mid = (left + right) >> 1
            if arr[mid] > arr[mid + 1]:
                right = mid
            else:
                left = mid + 1
        return left
```

#### Java

```java
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 1, right = arr.length - 2;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] > arr[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int peakIndexInMountainArray(vector<int>& arr) {
        int left = 1, right = arr.size() - 2;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] > arr[mid + 1])
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
};
```

#### Go

```go
func peakIndexInMountainArray(arr []int) int {
	left, right := 1, len(arr)-2
	for left < right {
		mid := (left + right) >> 1
		if arr[mid] > arr[mid+1] {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}
```

#### TypeScript

```ts
function peakIndexInMountainArray(arr: number[]): number {
    let left = 1,
        right = arr.length - 2;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (arr[mid] > arr[mid + 1]) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

#### Rust

```rust
impl Solution {
    pub fn peak_index_in_mountain_array(arr: Vec<i32>) -> i32 {
        let mut left = 1;
        let mut right = arr.len() - 2;
        while left < right {
            let mid = left + (right - left) / 2;
            if arr[mid] > arr[mid + 1] {
                right = mid;
            } else {
                left = left + 1;
            }
        }
        left as i32
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} arr
 * @return {number}
 */
var peakIndexInMountainArray = function (arr) {
    let left = 1;
    let right = arr.length - 2;
    while (left < right) {
        const mid = (left + right) >> 1;
        if (arr[mid] < arr[mid + 1]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return left;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
