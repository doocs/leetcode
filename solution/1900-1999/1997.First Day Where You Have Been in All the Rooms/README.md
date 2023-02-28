# [1997. 访问完所有房间的第一天](https://leetcode.cn/problems/first-day-where-you-have-been-in-all-the-rooms)

[English Version](/solution/1900-1999/1997.First%20Day%20Where%20You%20Have%20Been%20in%20All%20the%20Rooms/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你需要访问&nbsp;<code>n</code> 个房间，房间从 <code>0</code> 到 <code>n - 1</code> 编号。同时，每一天都有一个日期编号，从 <code>0</code> 开始，依天数递增。你每天都会访问一个房间。</p>

<p>最开始的第 <code>0</code> 天，你访问&nbsp;<code>0</code> 号房间。给你一个长度为 <code>n</code> 且 <strong>下标从 0 开始</strong> 的数组 <code>nextVisit</code> 。在接下来的几天中，你访问房间的 <strong>次序</strong> 将根据下面的 <strong>规则</strong> 决定：</p>

<ul>
	<li>假设某一天，你访问&nbsp;<code>i</code> 号房间。</li>
	<li>如果算上本次访问，访问&nbsp;<code>i</code> 号房间的次数为 <strong>奇数</strong> ，那么 <strong>第二天</strong> 需要访问&nbsp;<code>nextVisit[i]</code> 所指定的房间，其中 <code>0 &lt;= nextVisit[i] &lt;= i</code> 。</li>
	<li>如果算上本次访问，访问&nbsp;<code>i</code> 号房间的次数为 <strong>偶数</strong> ，那么 <strong>第二天</strong> 需要访问&nbsp;<code>(i + 1) mod n</code> 号房间。</li>
</ul>

<p>请返回你访问完所有房间的第一天的日期编号。题目数据保证总是存在这样的一天。由于答案可能很大，返回对 <code>10<sup>9</sup> + 7</code> 取余后的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nextVisit = [0,0]
<strong>输出：</strong>2
<strong>解释：</strong>
- 第 0 天，你访问房间 0 。访问 0 号房间的总次数为 1 ，次数为奇数。
&nbsp; 下一天你需要访问房间的编号是 nextVisit[0] = 0
- 第 1 天，你访问房间 0 。访问 0 号房间的总次数为 2 ，次数为偶数。
&nbsp; 下一天你需要访问房间的编号是 (0 + 1) mod 2 = 1
- 第 2 天，你访问房间 1 。这是你第一次完成访问所有房间的那天。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nextVisit = [0,0,2]
<strong>输出：</strong>6
<strong>解释：</strong>
你每天访问房间的次序是 [0,0,1,0,0,1,2,...] 。
第 6 天是你访问完所有房间的第一天。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nextVisit = [0,1,2,0]
<strong>输出：</strong>6
<strong>解释：</strong>
你每天访问房间的次序是 [0,0,1,1,2,2,3,...] 。
第 6 天是你访问完所有房间的第一天。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nextVisit.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nextVisit[i] &lt;= i</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i]$ 表示第一次访问第 $i$ 号房间的日期编号，那么答案就是 $f[n - 1]$。

我们考虑第一次到达第 $i-1$ 号房间的日期编号，记为 $f[i-1]$，此时需要花一天的时间回退到第 $nextVisit[i-1]$ 号房间，为什么是回退呢？因为题目限制了 $0 \leq nextVisit[i] \leq i$。

回退之后，此时第 $nextVisit[i-1]$ 号房间的访问为奇数次，而第 $[nextVisit[i-1]+1,..i-1]$ 号房间均被访问偶数次，那么这时候我们从第 $nextVisit[i-1]$ 号房间再次走到第 $i-1$ 号房间，就需要花费 $f[i-1] - f[nextVisit[i-1]]$ 天的时间，然后再花费一天的时间到达第 $i$ 号房间，因此 $f[i] = f[i-1] + 1 + f[i-1] - f[nextVisit[i-1]] + 1$。由于 $f[i]$ 可能很大，因此需要对 $10^9 + 7$ 取余，并且为了防止负数，需要加上 $10^9 + 7$。

最后返回 $f[n-1]$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为房间数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def firstDayBeenInAllRooms(self, nextVisit: List[int]) -> int:
        n = len(nextVisit)
        f = [0] * n
        mod = 10**9 + 7
        for i in range(1, n):
            f[i] = (f[i - 1] + 1 + f[i - 1] - f[nextVisit[i - 1]] + 1) % mod
        return f[-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.length;
        long[] f = new long[n];
        final int mod = (int) 1e9 + 7;
        for (int i = 1; i < n; ++i) {
            f[i] = (f[i - 1] + 1 + f[i - 1] - f[nextVisit[i - 1]] + 1 + mod) % mod;
        }
        return (int) f[n - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int firstDayBeenInAllRooms(vector<int>& nextVisit) {
        int n = nextVisit.size();
        vector<long long> f(n);
        const int mod = 1e9 + 7;
        for (int i = 1; i < n; ++i) {
            f[i] = (f[i - 1] + 1 + f[i - 1] - f[nextVisit[i - 1]] + 1 + mod) % mod;
        }
        return f[n - 1];
    }
};
```

### **Go**

```go
func firstDayBeenInAllRooms(nextVisit []int) int {
	n := len(nextVisit)
	f := make([]int, n)
	const mod = 1e9 + 7
	for i := 1; i < n; i++ {
		f[i] = (f[i-1] + 1 + f[i-1] - f[nextVisit[i-1]] + 1 + mod) % mod
	}
	return f[n-1]
}
```

### **...**

```

```

<!-- tabs:end -->
