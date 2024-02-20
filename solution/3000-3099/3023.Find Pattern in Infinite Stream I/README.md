# [3023. 在无限流中寻找模式 I](https://leetcode.cn/problems/find-pattern-in-infinite-stream-i)

[English Version](/solution/3000-3099/3023.Find%20Pattern%20in%20Infinite%20Stream%20I/README_EN.md)

<!-- tags:数组,字符串匹配,滑动窗口,哈希函数,滚动哈希 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二进制数组&nbsp;<code>pattern</code>&nbsp;和一个类 <code>InfiniteStream</code>&nbsp;的对象&nbsp;<code>stream</code>&nbsp;表示一个下标从 <strong>0</strong>&nbsp;开始的二进制位无限流。</p>

<p>类&nbsp;<code>InfiniteStream</code>&nbsp;包含下列函数：</p>

<ul>
	<li><code>int next()</code>：从流中读取 <strong>一个</strong>&nbsp;二进制位&nbsp;（是&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>）并返回。</li>
</ul>

<p>返回<em>&nbsp;<strong>第一个使得模式匹配流中读取的二进制位的 </strong>开始下标</em>。例如，如果模式是&nbsp;<code>[1, 0]</code>，第一个匹配是流中的高亮部分&nbsp;<code>[0, <strong><u>1, 0</u></strong>, 1, ...]</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入:</strong> stream = [1,1,1,0,1,1,1,...], pattern = [0,1]
<strong>输出:</strong> 3
<strong>解释:</strong> 模式 [0,1] 的第一次出现在流中高亮 [1,1,1,<strong><u>0,1</u></strong>,...]，从下标 3 开始。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入:</strong> stream = [0,0,0,0,...], pattern = [0]
<strong>输出:</strong> 0
<strong>解释:</strong> 模式 [0] 的第一次出现在流中高亮 [<strong><u>0</u></strong>,...]，从下标 0 开始。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入:</strong> stream = [1,0,1,1,0,1,1,0,1,...], pattern = [1,1,0,1]
<strong>输出:</strong> 2
<strong>解释:</strong> 模式 [1,1,0,1] 的第一次出现在流中高亮 [1,0,<strong><u>1,1,0,1</u></strong>,...]，从下标 2 开始。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= pattern.length &lt;= 100</code></li>
	<li><code>pattern</code>&nbsp;只包含&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>。</li>
	<li><code>stream</code> 只包含&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>。</li>
	<li>生成的输入使模式的开始下标在流的前&nbsp;<code>10<sup>5</sup></code>&nbsp;个二进制位中。</li>
</ul>

## 解法

### 方法一：位运算 + 滑动窗口

我们注意到，数组 $pattern$ 的长度不超过 $100$，因此，我们可以用两个 $64$ 位的整数 $a$ 和 $b$ 来表示 $pattern$ 左右两半的二进制数。

接下来，我们遍历数据流，同样维护两个 $64$ 位的整数 $x$ 和 $y$ 表示当前 $pattern$ 长度的窗口的二进制数。如果当前达到了窗口的长度，我们比较 $a$ 和 $x$ 是否相等，以及 $b$ 和 $y$ 是否相等，如果是，我们返回当前数据流的索引即可。

时间复杂度 $O(n + m)$，其中 $n$ 和 $m$ 分别是数据流和 $pattern$ 的元素个数。空间复杂度 $O(1)$。

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

<!-- end -->
