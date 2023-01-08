# [2528. 最大化城市的最小供电站数目](https://leetcode.cn/problems/maximize-the-minimum-powered-city)

[English Version](/solution/2500-2599/2528.Maximize%20the%20Minimum%20Powered%20City/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>stations</code>&nbsp;，其中&nbsp;<code>stations[i]</code>&nbsp;表示第 <code>i</code>&nbsp;座城市的供电站数目。</p>

<p>每个供电站可以在一定 <strong>范围</strong>&nbsp;内给所有城市提供电力。换句话说，如果给定的范围是&nbsp;<code>r</code>&nbsp;，在城市&nbsp;<code>i</code>&nbsp;处的供电站可以给所有满足&nbsp;<code>|i - j| &lt;= r</code> 且&nbsp;<code>0 &lt;= i, j &lt;= n - 1</code>&nbsp;的城市&nbsp;<code>j</code>&nbsp;供电。</p>

<ul>
	<li><code>|x|</code>&nbsp;表示 <code>x</code>&nbsp;的 <strong>绝对值</strong>&nbsp;。比方说，<code>|7 - 5| = 2</code>&nbsp;，<code>|3 - 10| = 7</code>&nbsp;。</li>
</ul>

<p>一座城市的 <strong>电量</strong>&nbsp;是所有能给它供电的供电站数目。</p>

<p>政府批准了可以额外建造 <code>k</code>&nbsp;座供电站，你需要决定这些供电站分别应该建在哪里，这些供电站与已经存在的供电站有相同的供电范围。</p>

<p>给你两个整数&nbsp;<code>r</code> 和&nbsp;<code>k</code>&nbsp;，如果以最优策略建造额外的发电站，返回所有城市中，最小供电站数目的最大值是多少。</p>

<p>这 <code>k</code>&nbsp;座供电站可以建在多个城市。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>stations = [1,2,4,5,0], r = 1, k = 2
<b>输出：</b>5
<b>解释：</b>
最优方案之一是把 2 座供电站都建在城市 1 。
每座城市的供电站数目分别为 [1,4,4,5,0] 。
- 城市 0 的供电站数目为 1 + 4 = 5 。
- 城市 1 的供电站数目为 1 + 4 + 4 = 9 。
- 城市 2 的供电站数目为 4 + 4 + 5 = 13 。
- 城市 3 的供电站数目为 5 + 4 = 9 。
- 城市 4 的供电站数目为 5 + 0 = 5 。
供电站数目最少是 5 。
无法得到更优解，所以我们返回 5 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>stations = [4,4,4,4], r = 0, k = 3
<b>输出：</b>4
<b>解释：</b>
无论如何安排，总有一座城市的供电站数目是 4 ，所以最优解是 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == stations.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= stations[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= r&nbsp;&lt;= n - 1</code></li>
	<li><code>0 &lt;= k&nbsp;&lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找 + 差分数组 + 贪心**

根据题目描述，最小供电站数目随着 $k$ 值的增大而增大，因此，我们可以用二分查找，找到一个最大的最小供电站数目，并且需要额外建造的供电站不超过 $k$ 座。

我们先利用差分数组以及前缀和算出初始时每座城市的供电站数目，记录在数组 $s$ 中，其中 $s[i]$ 表示第 $i$ 座城市的供电站数目。

接下来，我们定义二分查找的左边界为 $0$，右边界为 $2^{40}$。然后实现一个 $check(x, k)$ 函数，用于判断是否城市供电站数目的最小值是否可以为 $x$，使得额外建造的供电站不超过 $k$ 座。

函数 $check(x, k)$ 的实现逻辑是：

遍历每座城市，如果当前城市 $i$ 的供电站数目小于 $x$，此时我们可以贪心地在尽可能右边的位置上建造供电站，位置 $j = min(i + r, n - 1)$，这样可以使得供电站覆盖尽可能多的城市。过程中我们可以借助差分数组，给一段连续的位置加上某个值。如果需要额外建造的供电站数量超过 $k$，那么 $x$ 不满足条件，返回 `false`。否则遍历结束后，返回 `true`。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(n)$。其中 $n$ 为城市数量，而 $M$ 我们固定取 $2^{40}$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxPower(self, stations: List[int], r: int, k: int) -> int:
        def check(x, k):
            d = [0] * (n + 1)
            t = 0
            for i in range(n):
                t += d[i]
                dist = x - (s[i] + t)
                if dist > 0:
                    if k < dist:
                        return False
                    k -= dist
                    j = min(i + r, n - 1)
                    left, right = max(0, j - r), min(j + r, n - 1)
                    d[left] += dist
                    d[right + 1] -= dist
                    t += dist
            return True

        n = len(stations)
        d = [0] * (n + 1)
        for i, v in enumerate(stations):
            left, right = max(0, i - r), min(i + r, n - 1)
            d[left] += v
            d[right + 1] -= v
        s = list(accumulate(d))
        left, right = 0, 1 << 40
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid, k):
                left = mid
            else:
                right = mid - 1
        return left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private long[] s;
    private long[] d;
    private int n;

    public long maxPower(int[] stations, int r, int k) {
        n = stations.length;
        d = new long[n + 1];
        s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            int left = Math.max(0, i - r), right = Math.min(i + r, n - 1);
            d[left] += stations[i];
            d[right + 1] -= stations[i];
        }
        s[0] = d[0];
        for (int i = 1; i < n + 1; ++i) {
            s[i] = s[i - 1] + d[i];
        }
        long left = 0, right = 1l << 40;
        while (left < right) {
            long mid = (left + right + 1) >>> 1;
            if (check(mid, r, k)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(long x, int r, int k) {
        Arrays.fill(d, 0);
        long t = 0;
        for (int i = 0; i < n; ++i) {
            t += d[i];
            long dist = x - (s[i] + t);
            if (dist > 0) {
                if (k < dist) {
                    return false;
                }
                k -= dist;
                int j = Math.min(i + r, n - 1);
                int left = Math.max(0, j - r), right = Math.min(j + r, n - 1);
                d[left] += dist;
                d[right + 1] -= dist;
                t += dist;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long maxPower(vector<int>& stations, int r, int k) {
        int n = stations.size();
        long d[n + 1];
        memset(d, 0, sizeof d);
        for (int i = 0; i < n; ++i) {
            int left = max(0, i - r), right = min(i + r, n - 1);
            d[left] += stations[i];
            d[right + 1] -= stations[i];
        }
        long s[n + 1];
        s[0] = d[0];
        for (int i = 1; i < n + 1; ++i) {
            s[i] = s[i - 1] + d[i];
        }
        auto check = [&](long x, int k) {
            memset(d, 0, sizeof d);
            long t = 0;
            for (int i = 0; i < n; ++i) {
                t += d[i];
                long dist = x - (s[i] + t);
                if (dist > 0) {
                    if (k < dist) {
                        return false;
                    }
                    k -= dist;
                    int j = min(i + r, n - 1);
                    int left = max(0, j - r), right = min(j + r, n - 1);
                    d[left] += dist;
                    d[right + 1] -= dist;
                    t += dist;
                }
            }
            return true;
        };
        long left = 0, right = 1e12;
        while (left < right) {
            long mid = (left + right + 1) >> 1;
            if (check(mid, k)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};
```

### **Go**

```go
func maxPower(stations []int, r int, k int) int64 {
	n := len(stations)
	d := make([]int, n+1)
	s := make([]int, n+1)
	for i, v := range stations {
		left, right := max(0, i-r), min(i+r, n-1)
		d[left] += v
		d[right+1] -= v
	}
	s[0] = d[0]
	for i := 1; i < n+1; i++ {
		s[i] = s[i-1] + d[i]
	}
	check := func(x, k int) bool {
		d := make([]int, n+1)
		t := 0
		for i := range stations {
			t += d[i]
			dist := x - (s[i] + t)
			if dist > 0 {
				if k < dist {
					return false
				}
				k -= dist
				j := min(i+r, n-1)
				left, right := max(0, j-r), min(j+r, n-1)
				d[left] += dist
				d[right+1] -= dist
				t += dist
			}
		}
		return true
	}
	left, right := 0, 1<<40
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid, k) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return int64(left)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
