---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0702.Search%20in%20a%20Sorted%20Array%20of%20Unknown%20Size/README.md
tags:
    - 数组
    - 二分查找
    - 交互
---

<!-- problem:start -->

# [702. 搜索长度未知的有序数组 🔒](https://leetcode.cn/problems/search-in-a-sorted-array-of-unknown-size)

[English Version](/solution/0700-0799/0702.Search%20in%20a%20Sorted%20Array%20of%20Unknown%20Size/README_EN.md)

## 题目描述

<!-- description:start -->

<p>这是一个<strong>交互问题</strong>。</p>

<p>您有一个<strong>升序</strong>整数数组，其<strong>长度未知</strong>。您没有访问数组的权限，但是可以使用&nbsp;<code>ArrayReader</code>&nbsp;接口访问它。你可以调用&nbsp;<code>ArrayReader.get(i)</code>:</p>

<ul>
	<li>
	<p>返回数组第<code>i<sup>th</sup></code>个索引(<strong>0-indexed</strong>)处的值(即&nbsp;<code>secret[i]</code>)，或者</p>
	</li>
	<li>
	<p>如果&nbsp;<code>i</code>&nbsp; 超出了数组的边界，则返回&nbsp;<code>2<sup>31</sup>&nbsp;- 1</code></p>
	</li>
</ul>

<p>你也会得到一个整数 <code>target</code>。</p>

<p>如果存在<code>secret[k] == target</code>，请返回索引&nbsp;<code>k</code>&nbsp;的值；否则返回&nbsp;<code>-1</code></p>

<p>你必须写一个时间复杂度为&nbsp;<code>O(log n)</code>&nbsp;的算法。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> <code>secret</code> = [-1,0,3,5,9,12], <code>target</code> = 9
<strong>输出:</strong> 4
<strong>解释:</strong> 9 存在在 nums 中，下标为 4
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> <code>secret</code> = [-1,0,3,5,9,12], <code>target</code> = 2
<strong>输出:</strong> -1
<strong>解释:</strong> 2 不在数组中所以返回 -1</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= secret.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= secret[i], target &lt;= 10<sup>4</sup></code></li>
	<li><code>secret</code>&nbsp;严格递增</li>
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
# class ArrayReader:
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

#### Java

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

#### C++

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

#### Go

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
