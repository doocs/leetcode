---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0517.Super%20Washing%20Machines/README.md
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [517. 超级洗衣机](https://leetcode.cn/problems/super-washing-machines)

[English Version](/solution/0500-0599/0517.Super%20Washing%20Machines/README_EN.md)

## 题目描述

<!-- description:start -->

<p>假设有 <code>n</code><strong>&nbsp;</strong>台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。</p>

<p>在每一步操作中，你可以选择任意 <code>m</code> (<code>1 &lt;= m &lt;= n</code>) 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。</p>

<p>给定一个整数数组&nbsp;<code>machines</code> 代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的 <strong>最少的操作步数 </strong>。如果不能使每台洗衣机中衣物的数量相等，则返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>machines = [1,0,5]
<strong>输出：</strong>3
<strong>解释：</strong>
第一步:    1     0 &lt;-- 5    =&gt;    1     1     4
第二步:    1 &lt;-- 1 &lt;-- 4    =&gt;    2     1     3    
第三步:    2     1 &lt;-- 3    =&gt;    2     2     2   
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>machines = [0,3,0]
<strong>输出：</strong>2
<strong>解释：</strong>
第一步:    0 &lt;-- 3     0    =&gt;    1     2     0    
第二步:    1     2 --&gt; 0    =&gt;    1     1     1     
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>machines = [0,2,0]
<strong>输出：</strong>-1
<strong>解释：</strong>
不可能让所有三个洗衣机同时剩下相同数量的衣物。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == machines.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= machines[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

如果洗衣机内的衣服总数不能被洗衣机的数量整除，那么不可能使得每台洗衣机内的衣服数量相等，直接返回 $-1$。

否则，假设洗衣机内的衣服总数为 $s$，那么最终每台洗衣机内的衣服数量都会变为 $k = s / n$。

我们定义 $a_i$ 为第 $i$ 台洗衣机内的衣服数量与 $k$ 的差值，即 $a_i = \textit{machines}[i] - k$。若 $a_i > 0$，则表示第 $i$ 台洗衣机内有多余的衣服，需要向相邻的洗衣机传递；若 $a_i < 0$，则表示第 $i$ 台洗衣机内缺少衣服，需要从相邻的洗衣机获得。

我们将前 $i$ 台洗衣机的衣服数量差值之和定义为 $s_i = \sum_{j=0}^{i-1} a_j$，如果把前 $i$ 台洗衣机视为一组，其余的洗衣机视为另一组。那么若 $s_i$ 为正数，表示第一组洗衣机内有多余的衣服，需要向第二组洗衣机传递；若 $s_i$ 为负数，表示第一组洗衣机内缺少衣服，需要从第二组洗衣机获得。

那么有以下两种情况：

1. 两组之间的衣服，最多需要移动的次数为 $\max_{i=0}^{n-1} \lvert s_i \rvert$；
1. 组内某一台洗衣机的衣服数量过多，需要向左右两侧移出衣服，最多需要移动的次数为 $\max_{i=0}^{n-1} a_i$。

我们取两者的最大值即可。

时间复杂度 $O(n)$，其中 $n$ 为洗衣机的数量。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMinMoves(self, machines: List[int]) -> int:
        n = len(machines)
        k, mod = divmod(sum(machines), n)
        if mod:
            return -1
        ans = s = 0
        for x in machines:
            x -= k
            s += x
            ans = max(ans, abs(s), x)
        return ans
```

#### Java

```java
class Solution {
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int s = 0;
        for (int x : machines) {
            s += x;
        }
        if (s % n != 0) {
            return -1;
        }
        int k = s / n;
        s = 0;
        int ans = 0;
        for (int x : machines) {
            x -= k;
            s += x;
            ans = Math.max(ans, Math.max(Math.abs(s), x));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMinMoves(vector<int>& machines) {
        int n = machines.size();
        int s = accumulate(machines.begin(), machines.end(), 0);
        if (s % n) {
            return -1;
        }
        int k = s / n;
        s = 0;
        int ans = 0;
        for (int x : machines) {
            x -= k;
            s += x;
            ans = max({ans, abs(s), x});
        }
        return ans;
    }
};
```

#### Go

```go
func findMinMoves(machines []int) (ans int) {
	n := len(machines)
	s := 0
	for _, x := range machines {
		s += x
	}
	if s%n != 0 {
		return -1
	}
	k := s / n
	s = 0
	for _, x := range machines {
		x -= k
		s += x
		ans = max(ans, max(abs(s), x))
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function findMinMoves(machines: number[]): number {
    const n = machines.length;
    let s = machines.reduce((a, b) => a + b);
    if (s % n !== 0) {
        return -1;
    }
    const k = Math.floor(s / n);
    s = 0;
    let ans = 0;
    for (let x of machines) {
        x -= k;
        s += x;
        ans = Math.max(ans, Math.abs(s), x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
