# [2580. 统计将重叠区间合并成组的方案数](https://leetcode.cn/problems/count-ways-to-group-overlapping-ranges)

[English Version](/solution/2500-2599/2580.Count%20Ways%20to%20Group%20Overlapping%20Ranges/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组&nbsp;<code>ranges</code>&nbsp;，其中&nbsp;<code>ranges[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>&nbsp;表示&nbsp;<code>start<sub>i</sub></code>&nbsp;到&nbsp;<code>end<sub>i</sub></code>&nbsp;之间（包括二者）的所有整数都包含在第&nbsp;<code>i</code>&nbsp;个区间中。</p>

<p>你需要将&nbsp;<code>ranges</code>&nbsp;分成 <strong>两个</strong>&nbsp;组（可以为空），满足：</p>

<ul>
	<li>每个区间只属于一个组。</li>
	<li>两个有 <strong>交集</strong>&nbsp;的区间必须在 <strong>同一个&nbsp;</strong>组内。</li>
</ul>

<p>如果两个区间有至少 <strong>一个</strong>&nbsp;公共整数，那么这两个区间是 <b>有交集</b>&nbsp;的。</p>

<ul>
	<li>比方说，区间&nbsp;<code>[1, 3]</code> 和&nbsp;<code>[2, 5]</code>&nbsp;有交集，因为&nbsp;<code>2</code>&nbsp;和&nbsp;<code>3</code>&nbsp;在两个区间中都被包含。</li>
</ul>

<p>请你返回将 <code>ranges</code>&nbsp;划分成两个组的 <strong>总方案数</strong>&nbsp;。由于答案可能很大，将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>ranges = [[6,10],[5,15]]
<b>输出：</b>2
<b>解释：</b>
两个区间有交集，所以它们必须在同一个组内。
所以有两种方案：
- 将两个区间都放在第 1 个组中。
- 将两个区间都放在第 2 个组中。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>ranges = [[1,3],[10,20],[2,5],[4,8]]
<b>输出：</b>4
<b>解释：</b>
区间 [1,3] 和 [2,5] 有交集，所以它们必须在同一个组中。
同理，区间 [2,5] 和 [4,8] 也有交集，所以它们也必须在同一个组中。
所以总共有 4 种分组方案：
- 所有区间都在第 1 组。
- 所有区间都在第 2 组。
- 区间 [1,3] ，[2,5] 和 [4,8] 在第 1 个组中，[10,20] 在第 2 个组中。
- 区间 [1,3] ，[2,5] 和 [4,8] 在第 2 个组中，[10,20] 在第 1 个组中。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= ranges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>ranges[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 计数 + 快速幂**

我们可以先对区间进行排序，相交的区间进行合并，统计有多少个不相交的区间，记为 $cnt$。

每个不相交的区间可以选择放在第一组或第二组，所以方案数为 $2^{cnt}$。注意到 $2^{cnt}$ 可能很大，所以需要对 $10^9 + 7$ 取模。这里可以使用快速幂求解。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为区间个数。

我们也可以不使用快速幂，一旦发现有新的不相交的区间，就将方案数乘 $2$ 后对 $10^9 + 7$ 取模。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countWays(self, ranges: List[List[int]]) -> int:
        ranges.sort()
        cnt, mx = 0, -1
        for start, end in ranges:
            if start > mx:
                cnt += 1
            mx = max(mx, end)
        mod = 10**9 + 7
        return pow(2, cnt, mod)
```

```python
class Solution:
    def countWays(self, ranges: List[List[int]]) -> int:
        ranges.sort()
        mx = -1
        mod = 10**9 + 7
        ans = 1
        for start, end in ranges:
            if start > mx:
                ans = ans * 2 % mod
            mx = max(mx, end)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);
        int cnt = 0, mx = -1;
        for (int[] e : ranges) {
            if (e[0] > mx) {
                ++cnt;
            }
            mx = Math.max(mx, e[1]);
        }
        return qmi(2, cnt, (int) 1e9 + 7);
    }

    int qmi(long a, long k, int p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return (int) res;
    }
}
```

```java
class Solution {
    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);
        int mx = -1;
        int ans = 1;
        final int mod = (int) 1e9 + 7;
        for (int[] e : ranges) {
            if (e[0] > mx) {
                ans = ans * 2 % mod;
            }
            mx = Math.max(mx, e[1]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countWays(vector<vector<int>>& ranges) {
        sort(ranges.begin(), ranges.end());
        int cnt = 0, mx = -1;
        for (auto& e : ranges) {
            cnt += e[0] > mx;
            mx = max(mx, e[1]);
        }
        return qmi(2, cnt, 1e9 + 7);
    }

    int qmi(long a, long k, int p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }
};
```

```cpp
class Solution {
public:
    int countWays(vector<vector<int>>& ranges) {
        sort(ranges.begin(), ranges.end());
        int ans = 1, mx = -1;
        const int mod = 1e9 + 7;
        for (auto& e : ranges) {
            if (e[0] > mx) {
                ans = ans * 2 % mod;
            }
            mx = max(mx, e[1]);
        }
        return ans;
    }
};
```

### **Go**

```go
func countWays(ranges [][]int) int {
	sort.Slice(ranges, func(i, j int) bool { return ranges[i][0] < ranges[j][0] })
	cnt, mx := 0, -1
	for _, e := range ranges {
		if e[0] > mx {
			cnt++
		}
		if mx < e[1] {
			mx = e[1]
		}
	}
	return qmi(2, cnt, 1e9+7)
}

func qmi(a, k, p int) int {
	res := 1
	for k != 0 {
		if k&1 == 1 {
			res = res * a % p
		}
		k >>= 1
		a = a * a % p
	}
	return res
}
```

```go
func countWays(ranges [][]int) int {
	sort.Slice(ranges, func(i, j int) bool { return ranges[i][0] < ranges[j][0] })
	ans, mx := 1, -1
	const mod = 1e9 + 7
	for _, e := range ranges {
		if e[0] > mx {
			ans = ans * 2 % mod
		}
		if mx < e[1] {
			mx = e[1]
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function countWays(ranges: number[][]): number {
    ranges.sort((a, b) => a[0] - b[0]);
    let mx = -1;
    let ans = 1;
    const mod = 10 ** 9 + 7;
    for (const [start, end] of ranges) {
        if (start > mx) {
            ans = (ans * 2) % mod;
        }
        mx = Math.max(mx, end);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
