---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3023.Find%20Pattern%20in%20Infinite%20Stream%20I/README_EN.md
tags:
    - Array
    - String Matching
    - Sliding Window
    - Hash Function
    - Rolling Hash
---

<!-- problem:start -->

# [3023. Find Pattern in Infinite Stream I 🔒](https://leetcode.com/problems/find-pattern-in-infinite-stream-i)

[中文文档](/solution/3000-3099/3023.Find%20Pattern%20in%20Infinite%20Stream%20I/README.md)

## Description

<p>You are given a binary array <code>pattern</code> and an object <code>stream</code> of class <code>InfiniteStream</code> representing a <strong>0-indexed</strong> infinite stream of bits.</p>

<p>The class <code>InfiniteStream</code> contains the following function:</p>

<ul>
	<li><code>int next()</code>: Reads a <strong>single</strong> bit (which is either <code>0</code> or <code>1</code>) from the stream and returns it.</li>
</ul>

<p>Return <em>the <strong>first starting</strong> index where the pattern matches the bits read from the stream</em>. For example, if the pattern is <code>[1, 0]</code>, the first match is the highlighted part in the stream <code>[0, <strong><u>1, 0</u></strong>, 1, ...]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> stream = [1,1,1,0,1,1,1,...], pattern = [0,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The first occurrence of the pattern [0,1] is highlighted in the stream [1,1,1,<strong><u>0,1</u></strong>,...], which starts at index 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> stream = [0,0,0,0,...], pattern = [0]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The first occurrence of the pattern [0] is highlighted in the stream [<strong><u>0</u></strong>,...], which starts at index 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> stream = [1,0,1,1,0,1,1,0,1,...], pattern = [1,1,0,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The first occurrence of the pattern [1,1,0,1] is highlighted in the stream [1,0,<strong><u>1,1,0,1</u></strong>,...], which starts at index 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= pattern.length &lt;= 100</code></li>
	<li><code>pattern</code> consists only of <code>0</code> and <code>1</code>.</li>
	<li><code>stream</code> consists only of <code>0</code> and <code>1</code>.</li>
	<li>The input is generated such that the pattern&#39;s start index exists in the first <code>10<sup>5</sup></code> bits of the stream.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation + Sliding Window

We notice that the length of the array $pattern$ does not exceed $100$, therefore, we can use two $64$-bit integers $a$ and $b$ to represent the binary numbers of the left and right halves of $pattern$.

Next, we traverse the data stream, also maintaining two $64$-bit integers $x$ and $y$ to represent the binary numbers of the current window of the length of $pattern$. If the current length reaches the window length, we compare whether $a$ and $x$ are equal, and whether $b$ and $y$ are equal. If they are, we return the index of the current data stream.

The time complexity is $O(n + m)$, where $n$ and $m$ are the number of elements in the data stream and $pattern$ respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
# Definition for an infinite stream.
# class InfiniteStream:
#     def next(self) -> int:
#         pass
class Solution:
    def findPattern(
        self, stream: Optional["InfiniteStream"], pattern: List[int]
    ) -> int:
        a = b = 0
        m = len(pattern)
        half = m >> 1
        mask1 = (1 << half) - 1
        mask2 = (1 << (m - half)) - 1
        for i in range(half):
            a |= pattern[i] << (half - 1 - i)
        for i in range(half, m):
            b |= pattern[i] << (m - 1 - i)
        x = y = 0
        for i in count(1):
            v = stream.next()
            y = y << 1 | v
            v = y >> (m - half) & 1
            y &= mask2
            x = x << 1 | v
            x &= mask1
            if i >= m and a == x and b == y:
                return i - m
```

```java
/**
 * Definition for an infinite stream.
 * class InfiniteStream {
 *     public InfiniteStream(int[] bits);
 *     public int next();
 * }
 */
class Solution {
    public int findPattern(InfiniteStream infiniteStream, int[] pattern) {
        long a = 0, b = 0;
        int m = pattern.length;
        int half = m >> 1;
        long mask1 = (1L << half) - 1;
        long mask2 = (1L << (m - half)) - 1;
        for (int i = 0; i < half; ++i) {
            a |= (long) pattern[i] << (half - 1 - i);
        }
        for (int i = half; i < m; ++i) {
            b |= (long) pattern[i] << (m - 1 - i);
        }
        long x = 0, y = 0;
        for (int i = 1;; ++i) {
            int v = infiniteStream.next();
            y = y << 1 | v;
            v = (int) ((y >> (m - half)) & 1);
            y &= mask2;
            x = x << 1 | v;
            x &= mask1;
            if (i >= m && a == x && b == y) {
                return i - m;
            }
        }
    }
}
```

```cpp
/**
 * Definition for an infinite stream.
 * class InfiniteStream {
 * public:
 *     InfiniteStream(vector<int> bits);
 *     int next();
 * };
 */
class Solution {
public:
    int findPattern(InfiniteStream* stream, vector<int>& pattern) {
        long long a = 0, b = 0;
        int m = pattern.size();
        int half = m >> 1;
        long long mask1 = (1LL << half) - 1;
        long long mask2 = (1LL << (m - half)) - 1;
        for (int i = 0; i < half; ++i) {
            a |= (long long) pattern[i] << (half - 1 - i);
        }
        for (int i = half; i < m; ++i) {
            b |= (long long) pattern[i] << (m - 1 - i);
        }
        long x = 0, y = 0;
        for (int i = 1;; ++i) {
            int v = stream->next();
            y = y << 1 | v;
            v = (int) ((y >> (m - half)) & 1);
            y &= mask2;
            x = x << 1 | v;
            x &= mask1;
            if (i >= m && a == x && b == y) {
                return i - m;
            }
        }
    }
};
```

```go
/**
 * Definition for an infinite stream.
 * type InfiniteStream interface {
 *     Next() int
 * }
 */
 func findPattern(stream InfiniteStream, pattern []int) int {
	a, b := 0, 0
	m := len(pattern)
	half := m >> 1
	mask1 := (1 << half) - 1
	mask2 := (1 << (m - half)) - 1
	for i := 0; i < half; i++ {
		a |= pattern[i] << (half - 1 - i)
	}
	for i := half; i < m; i++ {
		b |= pattern[i] << (m - 1 - i)
	}
	x, y := 0, 0
	for i := 1; ; i++ {
		v := stream.Next()
		y = y<<1 | v
		v = (y >> (m - half)) & 1
		y &= mask2
		x = x<<1 | v
		x &= mask1
		if i >= m && a == x && b == y {
			return i - m
		}
	}
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
