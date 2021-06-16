# [702. 搜索长度未知的有序数组](https://leetcode-cn.com/problems/search-in-a-sorted-array-of-unknown-size)

[English Version](/solution/0700-0799/0702.Search%20in%20a%20Sorted%20Array%20of%20Unknown%20Size/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个升序整数数组，写一个函数搜索 <code>nums</code> 中数字&nbsp;<code>target</code>。如果 <code>target</code> 存在，返回它的下标，否则返回 <code>-1</code>。<strong>注意，这个数组的大小是未知的。</strong>你只可以通过 <code>ArrayReader</code> 接口访问这个数组，<code>ArrayReader.get(k)</code> 返回数组中第 <code>k</code> 个元素（下标从 0 开始）。</p>

<p>你可以认为数组中所有的整数都小于 <code>10000</code>。如果你访问数组越界，<code>ArrayReader.get</code> 会返回 <code>2147483647</code>。</p>

<p>&nbsp;</p>

<p><strong>样例 1：</strong></p>

<pre><strong>输入:</strong> <code>array</code> = [-1,0,3,5,9,12], <code>target</code> = 9
<strong>输出:</strong> 4
<strong>解释:</strong> 9 存在在 nums 中，下标为 4
</pre>

<p><strong>样例 2：</strong></p>

<pre><strong>输入:</strong> <code>array</code> = [-1,0,3,5,9,12], <code>target</code> = 2
<strong>输出:</strong> -1
<strong>解释:</strong> 2 不在数组中所以返回 -1</pre>

<p>&nbsp;</p>

<p><strong>注释 ：</strong></p>

<ol>
	<li>你可以认为数组中所有元素的值互不相同。</li>
	<li>数组元素的值域是&nbsp;<code>[-9999, 9999]</code>。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分法。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# """
# This is ArrayReader's API interface.
# You should not implement it, or speculate about its implementation
# """
#class ArrayReader:
#    def get(self, index: int) -> int:

class Solution:
    def search(self, reader, target):
        """
        :type reader: ArrayReader
        :type target: int
        :rtype: int
        """
        left, right = 0, 20000
        while left < right:
            mid = (left + right) >> 1
            if reader.get(mid) >= target:
                right = mid
            else:
                left = mid + 1
        return left if reader.get(left) == target else -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     public int get(int index) {}
 * }
 */

class Solution {
    public int search(ArrayReader reader, int target) {
        int left = 0, right = 20000;
        while (left < right) {
            int mid = left + right >> 1;
            if (reader.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return reader.get(left) == target ? left : -1;
    }
}
```

### **C++**

```cpp
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * class ArrayReader {
 *   public:
 *     int get(int index);
 * };
 */

class Solution {
public:
    int search(const ArrayReader& reader, int target) {
        int left = 0, right = 20000;
        while (left < right) {
            int mid = left + right >> 1;
            if (reader.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return reader.get(left) == target ? left : -1;
    }
};
```

### **Go**

```go
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * type ArrayReader struct {
 * }
 *
 * func (this *ArrayReader) get(index int) int {}
 */

func search(reader ArrayReader, target int) int {
	left, right := 0, 20000
	for left < right {
		mid := (left + right) >> 1
		if reader.get(mid) >= target {
			right = mid
		} else {
			left = mid + 1
		}
	}
	if reader.get(left) == target {
		return left
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
