---
comments: true
difficulty: Medium
tags:
    - Bit Manipulation
---

<!-- problem:start -->

# [201. Bitwise AND of Numbers Range](https://leetcode.com/problems/bitwise-and-of-numbers-range)

[中文文档](/solution/0200-0299/0201.Bitwise%20AND%20of%20Numbers%20Range/README.md)

## Mô tả

<!-- description:start -->

<p>Cho hai số nguyên <code>left</code> và <code>right</code> biểu diễn phạm vi <code>[left, right]</code>, hãy trả về <em>phép AND theo bit của tất cả các số trong phạm vi này, bao gồm cả hai đầu mút</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> left = 5, right = 7
<strong>Đầu ra:</strong> 4
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> left = 0, right = 0
<strong>Đầu ra:</strong> 0
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> left = 1, right = 2147483647
<strong>Đầu ra:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>0 &lt;= left &lt;= right &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Thực hiện phép AND theo bit với mọi số nguyên trong $[left,right]$ là không thể khi độ dài khoảng tiến gần đến $2^{31}$. Phép AND theo bit của một phạm vi liên tiếp chính là tiền tố nhị phân chung của các số trong phạm vi: các bit thấp hơn sẽ bị xóa bởi một số nào đó trong phạm vi.
>
> Khi $left < right$, hãy xóa bit 1 thấp nhất của $right$ ($right \mathrel{\&}= right-1$) cho đến khi $right \le left$. Giá trị $right$ còn lại chính là tiền tố chung đó.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rangeBitwiseAnd(self, left: int, right: int) -> int:
        while left < right:
            right &= right - 1
        return right
```

#### Java

```java
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            right &= (right - 1);
        }
        return right;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            right &= (right - 1);
        }
        return right;
    }
};
```

#### Go

```go
func rangeBitwiseAnd(left int, right int) int {
	for left < right {
		right &= (right - 1)
	}
	return right
}
```

#### JavaScript

```js
/**
 * @param {number} left
 * @param {number} right
 * @return {number}
 */
var rangeBitwiseAnd = function (left, right) {
    while (left < right) {
        right &= right - 1;
    }
    return right;
};
```

#### C#

```cs
public class Solution {
    public int RangeBitwiseAnd(int left, int right) {
        while (left < right) {
            right &= (right - 1);
        }
        return right;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
