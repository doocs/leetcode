# [1538. 找出隐藏数组中出现次数最多的元素](https://leetcode.cn/problems/guess-the-majority-in-a-hidden-array)

[English Version](/solution/1500-1599/1538.Guess%20the%20Majority%20in%20a%20Hidden%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组&nbsp;<code>nums</code>，且&nbsp;<code>nums</code>&nbsp;中的所有整数都为 <strong>0</strong> 或 <strong>1</strong>。你不能直接访问这个数组，你需要使用&nbsp;API <code>ArrayReader</code>&nbsp;，该 API 含有下列成员函数：</p>

<ul>
	<li><code>int query(int a, int b, int c, int d)</code>：其中&nbsp;<code>0 &lt;= a &lt; b &lt; c &lt; d&nbsp;&lt;&nbsp;ArrayReader.length()</code>&nbsp;。此函数查询以这四个参数为下标的元素并返回：
    <ul>
    	<li><strong>4 </strong>: 当这四个元素相同（0 或 1）时。</li>
    	<li><strong>2</strong>&nbsp;: 当其中三个元素的值等于 0 且一个元素等于 1 时，或当其中三个元素的值等于 1&nbsp;且一个元素等于 0&nbsp;时。</li>
    	<li><strong>0&nbsp;</strong>: 当其中两个元素等于 0 且两个元素等于 1 时。</li>
    </ul>
    </li>
    <li><code>int length()</code>：返回数组的长度。</li>
</ul>

<p>你可以调用&nbsp;<code>query()</code>&nbsp;最多&nbsp;<strong>2 * n 次</strong>，其中 n 等于&nbsp;<code>ArrayReader.length()</code>。</p>

<p>返回&nbsp;<code>nums</code>&nbsp;中出现次数最多的值的<strong>任意</strong>索引，若所有的值出现次数均相同，返回&nbsp;-1。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> nums = [0,0,1,0,1,1,1,1]
<strong>输出:</strong> 5
<strong>解释:</strong> API 的调用情况如下：
reader.length() // 返回 8，因为隐藏数组中有 8 个元素。
reader.query(0,1,2,3) // 返回 2，查询元素 nums[0], nums[1], nums[2], nums[3] 间的比较。
// 三个元素等于 0 且一个元素等于 1 或出现相反情况。
reader.query(4,5,6,7) // 返回 4，因为 nums[4], nums[5], nums[6], nums[7] 有相同值。
我们可以推断，最常出现的值在最后 4 个元素中。
索引 2, 4, 6, 7 也是正确答案。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [0,0,1,1,0]
<strong>输出:</strong> 0
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,0,1,0,1,0,1,0]
<strong>输出:</strong> -1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>5 &lt;= nums.length&nbsp;&lt;= 10^5</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>要找到出现次数最多的元素，需要至少调用&nbsp;<code>query()</code>&nbsp;多少次？</p>

## 解法

### 方法一：脑筋急转弯

我们先调用 `reader.query(0, 1, 2, 3)`，将得到的结果记为 $x$。

接下来，我们从下标 $4$ 开始遍历，每次调用 `reader.query(0, 1, 2, i)`，如果结果与 $x$ 相同，我们就将 $a$ 的值加一，否则将 $b$ 的值加一，同时将 $k$ 的值更新为 $i$。

然后，我们还需要判断下标 $0, 1, 2$ 与下标 $3$ 的元素是否相同，如果相同，我们将 $a$ 的值加一，否则将 $b$ 的值加一，同时将 $k$ 的值更新为对应的下标。

最后，如果 $a=b$，说明数组中 $0$ 和 $1$ 的个数相同，我们返回 $-1$；否则，如果 $a \gt b$，返回 $3$，否则返回 $k$。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
# """
# This is the ArrayReader's API interface.
# You should not implement it, or speculate about its implementation
# """
# class ArrayReader(object):
# 	 # Compares 4 different elements in the array
# 	 # return 4 if the values of the 4 elements are the same (0 or 1).
# 	 # return 2 if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
# 	 # return 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
#    def query(self, a: int, b: int, c: int, d: int) -> int:
#
# 	 # Returns the length of the array
#    def length(self) -> int:
#


class Solution:
    def guessMajority(self, reader: "ArrayReader") -> int:
        n = reader.length()
        x = reader.query(0, 1, 2, 3)
        a, b = 1, 0
        k = 0
        for i in range(4, n):
            if reader.query(0, 1, 2, i) == x:
                a += 1
            else:
                b += 1
                k = i

        y = reader.query(0, 1, 2, 4)
        if reader.query(1, 2, 3, 4) == y:
            a += 1
        else:
            b += 1
            k = 0
        if reader.query(0, 2, 3, 4) == y:
            a += 1
        else:
            b += 1
            k = 1
        if reader.query(0, 1, 3, 4) == y:
            a += 1
        else:
            b += 1
            k = 2

        if a == b:
            return -1
        return 3 if a > b else k
```

```java
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *   public:
 *     // Compares 4 different elements in the array
 *     // return 4 if the values of the 4 elements are the same (0 or 1).
 *     // return 2 if three elements have a value equal to 0 and one element has value equal to 1 or
 * vice versa.
 *     // return 0 : if two element have a value equal to 0 and two elements have a value equal
 * to 1. public int query(int a, int b, int c, int d);
 *
 *     // Returns the length of the array
 *     public int length();
 * };
 */

class Solution {
    public int guessMajority(ArrayReader reader) {
        int n = reader.length();
        int x = reader.query(0, 1, 2, 3);
        int a = 1, b = 0;
        int k = 0;
        for (int i = 4; i < n; ++i) {
            if (reader.query(0, 1, 2, i) == x) {
                ++a;
            } else {
                ++b;
                k = i;
            }
        }

        int y = reader.query(0, 1, 2, 4);
        if (reader.query(1, 2, 3, 4) == y) {
            ++a;
        } else {
            ++b;
            k = 0;
        }
        if (reader.query(0, 2, 3, 4) == y) {
            ++a;
        } else {
            ++b;
            k = 1;
        }
        if (reader.query(0, 1, 3, 4) == y) {
            ++a;
        } else {
            ++b;
            k = 2;
        }
        if (a == b) {
            return -1;
        }
        return a > b ? 3 : k;
    }
}
```

```cpp
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * class ArrayReader {
 *   public:
 *     // Compares 4 different elements in the array
 *     // return 4 if the values of the 4 elements are the same (0 or 1).
 *     // return 2 if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
 *     // return 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
 *     int query(int a, int b, int c, int d);
 *
 *     // Returns the length of the array
 *     int length();
 * };
 */

class Solution {
public:
    int guessMajority(ArrayReader& reader) {
        int n = reader.length();
        int x = reader.query(0, 1, 2, 3);
        int a = 1, b = 0;
        int k = 0;
        for (int i = 4; i < n; ++i) {
            if (reader.query(0, 1, 2, i) == x) {
                ++a;
            } else {
                ++b;
                k = i;
            }
        }

        int y = reader.query(0, 1, 2, 4);
        if (reader.query(1, 2, 3, 4) == y) {
            ++a;
        } else {
            ++b;
            k = 0;
        }
        if (reader.query(0, 2, 3, 4) == y) {
            ++a;
        } else {
            ++b;
            k = 1;
        }
        if (reader.query(0, 1, 3, 4) == y) {
            ++a;
        } else {
            ++b;
            k = 2;
        }
        if (a == b) {
            return -1;
        }
        return a > b ? 3 : k;
    }
};
```

```go
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * type ArrayReader struct {
 * }
 * // Compares 4 different elements in the array
 * // return 4 if the values of the 4 elements are the same (0 or 1).
 * // return 2 if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
 * // return 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
 * func (this *ArrayReader) query(a, b, c, d int) int {}
 *
 * // Returns the length of the array
 * func (this *ArrayReader) length() int {}
 */

func guessMajority(reader *ArrayReader) int {
	n := reader.length()
	x := reader.query(0, 1, 2, 3)
	a, b := 1, 0
	k := 0
	for i := 4; i < n; i++ {
		if reader.query(0, 1, 2, i) == x {
			a++
		} else {
			b++
			k = i
		}
	}

	y := reader.query(0, 1, 2, 4)
	if reader.query(1, 2, 3, 4) == y {
		a++
	} else {
		b++
		k = 0
	}
	if reader.query(0, 2, 3, 4) == y {
		a++
	} else {
		b++
		k = 1
	}
	if reader.query(0, 1, 3, 4) == y {
		a++
	} else {
		b++
		k = 2
	}
	if a == b {
		return -1
	}
	if a > b {
		return 3
	}
	return k
}
```

```ts
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * class ArrayReader {
 *     // Compares 4 different elements in the array
 *     // return 4 if the values of the 4 elements are the same (0 or 1).
 *     // return 2 if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
 *     // return 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
 *     query(a: number, b: number, c: number, d: number): number { };
 *
 *     // Returns the length of the array
 *     length(): number { };
 * };
 */

function guessMajority(reader: ArrayReader): number {
    const n = reader.length();
    const x = reader.query(0, 1, 2, 3);
    let a = 1;
    let b = 0;
    let k = 0;
    for (let i = 4; i < n; ++i) {
        if (reader.query(0, 1, 2, i) === x) {
            ++a;
        } else {
            ++b;
            k = i;
        }
    }
    const y = reader.query(0, 1, 2, 4);
    if (reader.query(1, 2, 3, 4) === y) {
        ++a;
    } else {
        ++b;
        k = 0;
    }
    if (reader.query(0, 2, 3, 4) === y) {
        ++a;
    } else {
        ++b;
        k = 1;
    }
    if (reader.query(0, 1, 3, 4) === y) {
        ++a;
    } else {
        ++b;
        k = 2;
    }
    if (a === b) {
        return -1;
    }
    return a > b ? 3 : k;
}
```

<!-- tabs:end -->

<!-- end -->
