# [LCP 12. 小张刷题计划](https://leetcode.cn/problems/xiao-zhang-shua-ti-ji-hua)

## 题目描述

<!-- 这里写题目描述 -->

<p>为了提高自己的代码能力，小张制定了 <code>LeetCode</code> 刷题计划，他选中了 <code>LeetCode</code> 题库中的 <code>n</code> 道题，编号从 <code>0</code> 到 <code>n-1</code>，并计划在 <code>m</code> 天内<strong>按照题目编号顺序</strong>刷完所有的题目（注意，小张不能用多天完成同一题）。</p>

<p>在小张刷题计划中，小张需要用 <code>time[i]</code> 的时间完成编号 <code>i</code> 的题目。此外，小张还可以使用场外求助功能，通过询问他的好朋友小杨题目的解法，可以省去该题的做题时间。为了防止&ldquo;小张刷题计划&rdquo;变成&ldquo;小杨刷题计划&rdquo;，小张每天最多使用一次求助。</p>

<p>我们定义 <code>m</code> 天中做题时间最多的一天耗时为 <code>T</code>（小杨完成的题目不计入做题总时间）。请你帮小张求出最小的 <code>T</code>是多少。</p>

<p><strong>示例 1：</strong></p>

<blockquote>
<p>输入：<code>time = [1,2,3,3], m = 2</code></p>

<p>输出：<code>3</code></p>

<p>解释：第一天小张完成前三题，其中第三题找小杨帮忙；第二天完成第四题，并且找小杨帮忙。这样做题时间最多的一天花费了 3 的时间，并且这个值是最小的。</p>
</blockquote>

<p><strong>示例 2：</strong></p>

<blockquote>
<p>输入：<code>time = [999,999,999], m = 4</code></p>

<p>输出：<code>0</code></p>

<p>解释：在前三天中，小张每天求助小杨一次，这样他可以在三天内完成所有的题目并不花任何时间。</p>
</blockquote>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= time.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= time[i] &lt;= 10000</code></li>
	<li><code>1 &lt;= m &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 二分查找**

我们可以将题意转换为，将题目最多分成 $m$ 组，每一组去掉最大值后不超过 $T$ ，求最小的满足条件的 $T$。

我们定义二分查找的左边界 $left=0$，右边界 $right=\sum_{i=0}^{n-1}time[i]$，二分查找的目标值为 $T$。

我们定义函数 $check(T)$，表示是否存在一种分组方案，使得每一组去掉最大值后不超过 $T$，并且分组数不超过 $m$。

我们可以用贪心的方法来判断是否存在这样的分组方案。我们从左到右遍历题目，将题目耗时加入当前总耗时 $s$，并更新当前分组的最大值 $mx$。如果当前总耗时 $s$ 减去当前分组的最大值 $mx$ 大于 $T$，则将当前题目作为新的分组的第一题，更新 $s$ 和 $mx$。继续遍历题目，直到遍历完所有题目。如果分组数不超过 $m$，则说明存在这样的分组方案，返回 $true$，否则返回 $false$。

时间复杂度 $O(n \times \log S)$，空间复杂度 $O(1)$。其中 $n$ 和 $S$ 分别为题目数量和题目总耗时。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minTime(self, time: List[int], m: int) -> int:
        def check(t):
            s = mx = 0
            d = 1
            for x in time:
                s += x
                mx = max(mx, x)
                if s - mx > t:
                    d += 1
                    s = mx = x
            return d <= m

        return bisect_left(range(sum(time)), True, key=check)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minTime(int[] time, int m) {
        int left = 0, right = 0;
        for (int x : time) {
            right += x;
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(mid, time, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int t, int[] time, int m) {
        int s = 0, mx = 0;
        int d = 1;
        for (int x : time) {
            s += x;
            mx = Math.max(mx, x);
            if (s - mx > t) {
                s = x;
                mx = x;
                ++d;
            }
        }
        return d <= m;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minTime(vector<int>& time, int m) {
        int left = 0, right = 0;
        for (int x : time) {
            right += x;
        }
        auto check = [&](int t) -> bool {
            int s = 0, mx = 0;
            int d = 1;
            for (int x : time) {
                s += x;
                mx = max(mx, x);
                if (s - mx > t) {
                    s = x;
                    mx = x;
                    ++d;
                }
            }
            return d <= m;
        };
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

### **Go**

```go
func minTime(time []int, m int) int {
	right := 0
	for _, x := range time {
		right += x
	}
	return sort.Search(right, func(t int) bool {
		s, mx := 0, 0
		d := 1
		for _, x := range time {
			s += x
			mx = max(mx, x)
			if s-mx > t {
				s, mx = x, x
				d++
			}
		}
		return d <= m
	})
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
