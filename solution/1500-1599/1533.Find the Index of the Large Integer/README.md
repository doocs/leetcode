---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1533.Find%20the%20Index%20of%20the%20Large%20Integer/README.md
tags:
    - 数组
    - 二分查找
    - 交互
---

<!-- problem:start -->

# [1533. 找到最大整数的索引 🔒](https://leetcode.cn/problems/find-the-index-of-the-large-integer)

[English Version](/solution/1500-1599/1533.Find%20the%20Index%20of%20the%20Large%20Integer/README_EN.md)

## 题目描述

<!-- description:start -->

<p>我们有这样一个整数数组&nbsp;<code>arr</code>&nbsp;，除了一个最大的整数外，其他所有整数都相等。你不能直接访问该数组，你需要通过&nbsp;<strong>API</strong> <code>ArrayReader</code>&nbsp;来间接访问，这个 API&nbsp;有以下成员函数：</p>

<ul>
	<li><code>int compareSub(int l, int r, int x, int y)</code>：其中&nbsp;<code>0 &lt;= l, r, x, y &lt;&nbsp;ArrayReader.length()</code>，&nbsp;<code>l &lt;= r</code>&nbsp;且&nbsp;<code>x &lt;= y</code>。这个函数比较子数组&nbsp;<code>arr[l..r]</code>&nbsp;与子数组&nbsp;<code>arr[x..y]</code>&nbsp;的和。该函数返回：

    <ul>
    	<li><strong>1</strong>&nbsp;若&nbsp;<code>arr[l]+arr[l+1]+...+arr[r] &gt; arr[x]+arr[x+1]+...+arr[y]</code>&nbsp;。</li>
    	<li><strong>0</strong>&nbsp;若&nbsp;<code>arr[l]+arr[l+1]+...+arr[r] == arr[x]+arr[x+1]+...+arr[y]</code>&nbsp;。</li>
    	<li><strong>-1</strong>&nbsp;若&nbsp;<code>arr[l]+arr[l+1]+...+arr[r] &lt; arr[x]+arr[x+1]+...+arr[y]</code>&nbsp;。</li>
    </ul>
    </li>
    <li><code>int length()</code>：返回数组的长度。</li>

</ul>

<p>你最多可以调用函数&nbsp;<code>compareSub()</code>&nbsp;<strong>20 次</strong>。你可以认为这两个函数的时间复杂度都为&nbsp;<code>O(1)</code>&nbsp;。</p>

<p>返回<em>&nbsp;<code>arr</code>&nbsp;中最大整数的索引。</em></p>

<ul>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> arr = [7,7,7,7,10,7,7,7]
<strong>输出:</strong> 4
<strong>解释:</strong> API 的调用如下：
reader.compareSub(0, 0, 1, 1) // 返回 0。比较子数组 (0, 0) 与子数组 (1, 1) （即比较 arr[0] 和 arr[1]）。
因此我们知道 arr[0] 和 arr[1] 不包含最大元素。
reader.compareSub(2, 2, 3, 3) // 返回 0。我们可以排除 arr[2] 和 arr[3]。
reader.compareSub(4, 4, 5, 5) // 返回 1。因此，可以确定 arr[4] 是数组中最大的元素。
注意，我们只调用了 3 次 compareSub，所以这个答案是有效的。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [6,6,12]
<strong>输出:</strong> 2
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length&nbsp;&lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 100</code></li>
	<li><code>arr</code>&nbsp;中除一个最大元素外，其余所有元素都相等。</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶</strong></p>

<ul>
	<li>如果&nbsp;<code>arr</code>&nbsp;中有两个整数比其他数大呢？</li>
	<li>如果有一个数比其他数大，另一个数比其他数小呢？</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
# """
# This is ArrayReader's API interface.
# You should not implement it, or speculate about its implementation
# """
# class ArrayReader(object):
# 	 # Compares the sum of arr[l..r] with the sum of arr[x..y]
# 	 # return 1 if sum(arr[l..r]) > sum(arr[x..y])
# 	 # return 0 if sum(arr[l..r]) == sum(arr[x..y])
# 	 # return -1 if sum(arr[l..r]) < sum(arr[x..y])
#    def compareSub(self, l: int, r: int, x: int, y: int) -> int:
#
# 	 # Returns the length of the array
#    def length(self) -> int:
#


class Solution:
    def getIndex(self, reader: 'ArrayReader') -> int:
        left, right = 0, reader.length() - 1
        while left < right:
            t1, t2, t3 = (
                left,
                left + (right - left) // 3,
                left + ((right - left) // 3) * 2 + 1,
            )
            cmp = reader.compareSub(t1, t2, t2 + 1, t3)
            if cmp == 0:
                left = t3 + 1
            elif cmp == 1:
                right = t2
            else:
                left, right = t2 + 1, t3
        return left
```

#### Java

```java
/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     // Compares the sum of arr[l..r] with the sum of arr[x..y]
 *     // return 1 if sum(arr[l..r]) > sum(arr[x..y])
 *     // return 0 if sum(arr[l..r]) == sum(arr[x..y])
 *     // return -1 if sum(arr[l..r]) < sum(arr[x..y])
 *     public int compareSub(int l, int r, int x, int y) {}
 *
 *     // Returns the length of the array
 *     public int length() {}
 * }
 */

class Solution {
    public int getIndex(ArrayReader reader) {
        int left = 0, right = reader.length() - 1;
        while (left < right) {
            int t1 = left, t2 = left + (right - left) / 3, t3 = left + (right - left) / 3 * 2 + 1;
            int cmp = reader.compareSub(t1, t2, t2 + 1, t3);
            if (cmp == 0) {
                left = t3 + 1;
            } else if (cmp == 1) {
                right = t2;
            } else {
                left = t2 + 1;
                right = t3;
            }
        }
        return left;
    }
}
```

#### C++

```cpp
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * class ArrayReader {
 *   public:
 *     // Compares the sum of arr[l..r] with the sum of arr[x..y]
 *     // return 1 if sum(arr[l..r]) > sum(arr[x..y])
 *     // return 0 if sum(arr[l..r]) == sum(arr[x..y])
 *     // return -1 if sum(arr[l..r]) < sum(arr[x..y])
 *     int compareSub(int l, int r, int x, int y);
 *
 *     // Returns the length of the array
 *     int length();
 * };
 */

class Solution {
public:
    int getIndex(ArrayReader& reader) {
        int left = 0, right = reader.length() - 1;
        while (left < right) {
            int t1 = left, t2 = left + (right - left) / 3, t3 = left + (right - left) / 3 * 2 + 1;
            int cmp = reader.compareSub(t1, t2, t2 + 1, t3);
            if (cmp == 0) {
                left = t3 + 1;
            } else if (cmp == 1) {
                right = t2;
            } else {
                left = t2 + 1;
                right = t3;
            }
        }
        return left;
    }
};
```

#### Go

```go
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * type ArrayReader struct {
 * }
 * // Compares the sum of arr[l..r] with the sum of arr[x..y]
 * // return 1 if sum(arr[l..r]) > sum(arr[x..y])
 * // return 0 if sum(arr[l..r]) == sum(arr[x..y])
 * // return -1 if sum(arr[l..r]) < sum(arr[x..y])
 * func (this *ArrayReader) compareSub(l, r, x, y int) int {}
 *
 * // Returns the length of the array
 * func (this *ArrayReader) length() int {}
 */

func getIndex(reader *ArrayReader) int {
	left, right := 0, reader.length()-1
	for left < right {
		t1, t2, t3 := left, left+(right-left)/3, left+(right-left)/3*2+1
		cmp := reader.compareSub(t1, t2, t2+1, t3)
		if cmp == 0 {
			left = t3 + 1
		} else if cmp == 1 {
			right = t2
		} else {
			left, right = t2+1, t3
		}
	}
	return left
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
