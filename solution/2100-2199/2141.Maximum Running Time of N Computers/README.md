# [2141. 同时运行 N 台电脑的最长时间](https://leetcode.cn/problems/maximum-running-time-of-n-computers)

[English Version](/solution/2100-2199/2141.Maximum%20Running%20Time%20of%20N%20Computers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有&nbsp;<code>n</code>&nbsp;台电脑。给你整数&nbsp;<code>n</code>&nbsp;和一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>batteries</code>&nbsp;，其中第&nbsp;<code>i</code>&nbsp;个电池可以让一台电脑 <strong>运行&nbsp;</strong><code>batteries[i]</code>&nbsp;分钟。你想使用这些电池让&nbsp;<strong>全部</strong>&nbsp;<code>n</code>&nbsp;台电脑 <b>同时</b>&nbsp;运行。</p>

<p>一开始，你可以给每台电脑连接 <strong>至多一个电池</strong>&nbsp;。然后在任意整数时刻，你都可以将一台电脑与它的电池断开连接，并连接另一个电池，你可以进行这个操作 <strong>任意次</strong>&nbsp;。新连接的电池可以是一个全新的电池，也可以是别的电脑用过的电池。断开连接和连接新的电池不会花费任何时间。</p>

<p>注意，你不能给电池充电。</p>

<p>请你返回你可以让 <code>n</code>&nbsp;台电脑同时运行的 <strong>最长</strong>&nbsp;分钟数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2141.Maximum%20Running%20Time%20of%20N%20Computers/images/example1-fit.png" style="width: 762px; height: 150px;"></p>

<pre><b>输入：</b>n = 2, batteries = [3,3,3]
<b>输出：</b>4
<b>解释：</b>
一开始，将第一台电脑与电池 0 连接，第二台电脑与电池 1 连接。
2 分钟后，将第二台电脑与电池 1 断开连接，并连接电池 2 。注意，电池 0 还可以供电 1 分钟。
在第 3 分钟结尾，你需要将第一台电脑与电池 0 断开连接，然后连接电池 1 。
在第 4 分钟结尾，电池 1 也被耗尽，第一台电脑无法继续运行。
我们最多能同时让两台电脑同时运行 4 分钟，所以我们返回 4 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2141.Maximum%20Running%20Time%20of%20N%20Computers/images/example2.png" style="width: 629px; height: 150px;"></p>

<pre><b>输入：</b>n = 2, batteries = [1,1,1,1]
<b>输出：</b>2
<b>解释：</b>
一开始，将第一台电脑与电池 0 连接，第二台电脑与电池 2 连接。
一分钟后，电池 0 和电池 2 同时耗尽，所以你需要将它们断开连接，并将电池 1 和第一台电脑连接，电池 3 和第二台电脑连接。
1 分钟后，电池 1 和电池 3 也耗尽了，所以两台电脑都无法继续运行。
我们最多能让两台电脑同时运行 2 分钟，所以我们返回 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= batteries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= batteries[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

我们注意到，如果我们可以让 $n$ 台电脑同时运行 $t$ 分钟，那么我们也可以让 $n$ 台电脑同时运行 $t' \le t$ 分钟，这存在着单调性。因此，我们可以使用二分查找的方法找到最大的 $t$。

我们定义二分查找的左边界 $l=0$，右边界 $r=\sum_{i=0}^{n-1} batteries[i]$。每次二分查找的过程中，我们使用一个变量 $mid$ 表示当前的中间值，即 $mid = (l + r + 1) >> 1$。我们判断是否存在一种方案，使得 $n$ 台电脑同时运行 $mid$ 分钟。如果存在，那么我们就将 $l$ 更新为 $mid$，否则我们将 $r$ 更新为 $mid - 1$。最后，我们返回 $l$ 即为答案。

问题转化为如何判断是否存在一种方案，使得 $n$ 台电脑同时运行 $mid$ 分钟。如果一个电池可以运行的分钟数大于 $mid$，由于电脑同时运行 $mid$ 分钟，而一个电池同一时间只能供电一台电脑，因此我们只能使用这个电池 $mid$ 分钟。如果一个电池可以运行的分钟数小于等于 $mid$，我们可以使用这个电池的全部电量。因此，我们统计所有电池可以供电的分钟数之和 $s$，如果 $s \ge n \times mid$，那么我们就可以使得 $n$ 台电脑同时运行 $mid$ 分钟。

时间复杂度 $O(n \times \log M)$，其中 $M$ 为所有电池的电量之和，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxRunTime(self, n: int, batteries: List[int]) -> int:
        l, r = 0, sum(batteries)
        while l < r:
            mid = (l + r + 1) >> 1
            if sum(min(x, mid) for x in batteries) >= n * mid:
                l = mid
            else:
                r = mid - 1
        return l
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long l = 0, r = 0;
        for (int x : batteries) {
            r += x;
        }
        while (l < r) {
            long mid = (l + r + 1) >> 1;
            long s = 0;
            for (int x : batteries) {
                s += Math.min(mid, x);
            }
            if (s >= n * mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long maxRunTime(int n, vector<int>& batteries) {
        long long l = 0, r = 0;
        for (int x : batteries) {
            r += x;
        }
        while (l < r) {
            long long mid = (l + r + 1) >> 1;
            long long s = 0;
            for (int x : batteries) {
                s += min(1LL * x, mid);
            }
            if (s >= n * mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
```

### **Go**

```go
func maxRunTime(n int, batteries []int) int64 {
	l, r := 0, 0
	for _, x := range batteries {
		r += x
	}
	for l < r {
		mid := (l + r + 1) >> 1
		s := 0
		for _, x := range batteries {
			s += min(x, mid)
		}
		if s >= n*mid {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return int64(l)
}
```

### **TypeScript**

```ts
function maxRunTime(n: number, batteries: number[]): number {
    let l = 0n;
    let r = 0n;
    for (const x of batteries) {
        r += BigInt(x);
    }
    while (l < r) {
        const mid = (l + r + 1n) >> 1n;
        let s = 0n;
        for (const x of batteries) {
            s += BigInt(Math.min(x, Number(mid)));
        }
        if (s >= mid * BigInt(n)) {
            l = mid;
        } else {
            r = mid - 1n;
        }
    }
    return Number(l);
}
```

### **Rust**

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn max_run_time(n: i32, batteries: Vec<i32>) -> i64 {

        // First sort the batteries
        let mut batteries = batteries;
        let m = batteries.len() as i32;
        batteries.sort();

        let mut extra_sum: i64 = 0;
        for i in 0..(m - n) as usize {
            extra_sum += batteries[i] as i64;
        }

        let mut i = (m - n) as usize;
        let mut cur_height = batteries[i];
        let mut ret = cur_height as i64;
        while extra_sum != 0 {
            if i + 1 == m as usize {
                assert!(cur_height == *batteries.last().unwrap());
                ret += extra_sum / n as i64;
                break;
            }

            if batteries[i] == batteries[i + 1] {
                i += 1;
                continue;
            }

            let diff = extra_sum / (i - (m - n) as usize + 1) as i64;

            if (cur_height as i64 + diff) <= batteries[i + 1] as i64 {
                ret = cur_height as i64 + diff;
                break;
            } else {
                extra_sum -= (batteries[i + 1] - batteries[i]) as i64 * (i - (m - n) as usize + 1) as i64;
                ret = batteries[i + 1] as i64;
            }

            i += 1;
            if i != m as usize {
                cur_height = batteries[i];
            }
        }

        ret
    }
}
```

### **...**

```

```

<!-- tabs:end -->
