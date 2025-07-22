---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2848.Points%20That%20Intersect%20With%20Cars/README.md
rating: 1229
source: 第 362 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 前缀和
---

<!-- problem:start -->

# [2848. 与车相交的点](https://leetcode.cn/problems/points-that-intersect-with-cars)

[English Version](/solution/2800-2899/2848.Points%20That%20Intersect%20With%20Cars/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的二维整数数组 <code>nums</code> 表示汽车停放在数轴上的坐标。对于任意下标 <code>i</code>，<code>nums[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> ，其中 <code>start<sub>i</sub></code> 是第 <code>i</code> 辆车的起点，<code>end<sub>i</sub></code> 是第 <code>i</code> 辆车的终点。</p>

<p>返回数轴上被车 <strong>任意部分</strong> 覆盖的整数点的数目。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [[3,6],[1,5],[4,7]]
<strong>输出：</strong>7
<strong>解释：</strong>从 1 到 7 的所有点都至少与一辆车相交，因此答案为 7 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [[1,3],[5,8]]
<strong>输出：</strong>7
<strong>解释：</strong>1、2、3、5、6、7、8 共计 7 个点满足至少与一辆车相交，因此答案为 7 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>nums[i].length == 2</code></li>
	<li><code><font face="monospace">1 &lt;= start<sub>i</sub>&nbsp;&lt;= end<sub>i</sub>&nbsp;&lt;= 100</font></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：差分数组

根据题目描述，我们需要给每个区间 $[\textit{start}_i, \textit{end}_i]$ 增加一个车辆，我们可以使用差分数组来实现。

我们定义一个长度为 $102$ 的数组 $d$，对于每个区间 $[\textit{start}_i, \textit{end}_i]$，我们将 $d[\textit{start}_i]$ 加 $1$，将 $d[\textit{end}_i + 1]$ 减 $1$。

最后，我们对 $d$ 进行前缀和运算，统计前缀和大于 $0$ 的个数即可。

时间复杂度 $O(n + m)$，空间复杂度 $O(m)$，其中 $n$ 是给定数组的长度，而 $m$ 是数组中的最大值，本题中 $m \leq 102$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfPoints(self, nums: List[List[int]]) -> int:
        m = 102
        d = [0] * m
        for start, end in nums:
            d[start] += 1
            d[end + 1] -= 1
        return sum(s > 0 for s in accumulate(d))
```

#### Java

```java
class Solution {
    public int numberOfPoints(List<List<Integer>> nums) {
        int[] d = new int[102];
        for (var e : nums) {
            int start = e.get(0), end = e.get(1);
            ++d[start];
            --d[end + 1];
        }
        int ans = 0, s = 0;
        for (int x : d) {
            s += x;
            if (s > 0) {
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfPoints(vector<vector<int>>& nums) {
        int d[102]{};
        for (const auto& e : nums) {
            int start = e[0], end = e[1];
            ++d[start];
            --d[end + 1];
        }
        int ans = 0, s = 0;
        for (int x : d) {
            s += x;
            ans += s > 0;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfPoints(nums [][]int) (ans int) {
	d := [102]int{}
	for _, e := range nums {
		start, end := e[0], e[1]
		d[start]++
		d[end+1]--
	}
	s := 0
	for _, x := range d {
		s += x
		if s > 0 {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function numberOfPoints(nums: number[][]): number {
    const d: number[] = Array(102).fill(0);
    for (const [start, end] of nums) {
        ++d[start];
        --d[end + 1];
    }
    let ans = 0;
    let s = 0;
    for (const x of d) {
        s += x;
        ans += s > 0 ? 1 : 0;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：哈希表 + 差分 + 排序

如果题目的区间范围较大，我们可以使用哈希表来存储区间的起点和终点，然后对哈希表的键进行排序，再进行前缀和统计。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为给定数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfPoints(self, nums: List[List[int]]) -> int:
        d = defaultdict(int)
        for start, end in nums:
            d[start] += 1
            d[end + 1] -= 1
        ans = s = last = 0
        for cur, v in sorted(d.items()):
            if s > 0:
                ans += cur - last
            s += v
            last = cur
        return ans
```

#### Java

```java
class Solution {
    public int numberOfPoints(List<List<Integer>> nums) {
        TreeMap<Integer, Integer> d = new TreeMap<>();
        for (var e : nums) {
            int start = e.get(0), end = e.get(1);
            d.merge(start, 1, Integer::sum);
            d.merge(end + 1, -1, Integer::sum);
        }
        int ans = 0, s = 0, last = 0;
        for (var e : d.entrySet()) {
            int cur = e.getKey(), v = e.getValue();
            if (s > 0) {
                ans += cur - last;
            }
            s += v;
            last = cur;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfPoints(vector<vector<int>>& nums) {
        map<int, int> d;
        for (const auto& e : nums) {
            int start = e[0], end = e[1];
            ++d[start];
            --d[end + 1];
        }
        int ans = 0, s = 0, last = 0;
        for (const auto& [cur, v] : d) {
            if (s > 0) {
                ans += cur - last;
            }
            s += v;
            last = cur;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfPoints(nums [][]int) (ans int) {
	d := map[int]int{}
	for _, e := range nums {
		start, end := e[0], e[1]
		d[start]++
		d[end+1]--
	}
	keys := []int{}
	for k := range d {
		keys = append(keys, k)
	}
	s, last := 0, 0
	sort.Ints(keys)
	for _, cur := range keys {
		if s > 0 {
			ans += cur - last
		}
		s += d[cur]
		last = cur
	}
	return
}
```

#### TypeScript

```ts
function numberOfPoints(nums: number[][]): number {
    const d = new Map<number, number>();
    for (const [start, end] of nums) {
        d.set(start, (d.get(start) || 0) + 1);
        d.set(end + 1, (d.get(end + 1) || 0) - 1);
    }
    const keys = [...d.keys()].sort((a, b) => a - b);
    let [ans, s, last] = [0, 0, 0];
    for (const cur of keys) {
        if (s > 0) {
            ans += cur - last;
        }
        s += d.get(cur)!;
        last = cur;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
