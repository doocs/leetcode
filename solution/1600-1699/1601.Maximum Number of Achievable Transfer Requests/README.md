---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1601.Maximum%20Number%20of%20Achievable%20Transfer%20Requests/README.md
rating: 2118
source: 第 208 场周赛 Q4
tags:
    - 位运算
    - 数组
    - 回溯
    - 枚举
---

# [1601. 最多可达成的换楼请求数目](https://leetcode.cn/problems/maximum-number-of-achievable-transfer-requests)

[English Version](/solution/1600-1699/1601.Maximum%20Number%20of%20Achievable%20Transfer%20Requests/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们有&nbsp;<code>n</code>&nbsp;栋楼，编号从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;。每栋楼有若干员工。由于现在是换楼的季节，部分员工想要换一栋楼居住。</p>

<p>给你一个数组 <code>requests</code>&nbsp;，其中&nbsp;<code>requests[i] = [from<sub>i</sub>, to<sub>i</sub>]</code>&nbsp;，表示一个员工请求从编号为&nbsp;<code>from<sub>i</sub></code>&nbsp;的楼搬到编号为&nbsp;<code>to<sub>i</sub></code><sub>&nbsp;</sub>的楼。</p>

<p>一开始&nbsp;<strong>所有楼都是满的</strong>，所以从请求列表中选出的若干个请求是可行的需要满足 <strong>每栋楼员工净变化为 0&nbsp;</strong>。意思是每栋楼 <strong>离开</strong>&nbsp;的员工数目 <strong>等于</strong>&nbsp;该楼 <strong>搬入</strong>&nbsp;的员工数数目。比方说&nbsp;<code>n = 3</code>&nbsp;且两个员工要离开楼&nbsp;<code>0</code>&nbsp;，一个员工要离开楼&nbsp;<code>1</code>&nbsp;，一个员工要离开楼 <code>2</code>&nbsp;，如果该请求列表可行，应该要有两个员工搬入楼&nbsp;<code>0</code>&nbsp;，一个员工搬入楼&nbsp;<code>1</code>&nbsp;，一个员工搬入楼&nbsp;<code>2</code>&nbsp;。</p>

<p>请你从原请求列表中选出若干个请求，使得它们是一个可行的请求列表，并返回所有可行列表中最大请求数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1601.Maximum%20Number%20of%20Achievable%20Transfer%20Requests/images/move1.jpg" style="height: 406px; width: 600px;"></p>

<pre><strong>输入：</strong>n = 5, requests = [[0,1],[1,0],[0,1],[1,2],[2,0],[3,4]]
<strong>输出：</strong>5
<strong>解释：</strong>请求列表如下：
从楼 0 离开的员工为 x 和 y ，且他们都想要搬到楼 1 。
从楼 1 离开的员工为 a 和 b ，且他们分别想要搬到楼 2 和 0 。
从楼 2 离开的员工为 z ，且他想要搬到楼 0 。
从楼 3 离开的员工为 c ，且他想要搬到楼 4 。
没有员工从楼 4 离开。
我们可以让 x 和 b 交换他们的楼，以满足他们的请求。
我们可以让 y，a 和 z 三人在三栋楼间交换位置，满足他们的要求。
所以最多可以满足 5 个请求。</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1601.Maximum%20Number%20of%20Achievable%20Transfer%20Requests/images/move2.jpg" style="height: 327px; width: 450px;"></p>

<pre><strong>输入：</strong>n = 3, requests = [[0,0],[1,2],[2,1]]
<strong>输出：</strong>3
<strong>解释：</strong>请求列表如下：
从楼 0 离开的员工为 x ，且他想要回到原来的楼 0 。
从楼 1 离开的员工为 y ，且他想要搬到楼 2 。
从楼 2 离开的员工为 z ，且他想要搬到楼 1 。
我们可以满足所有的请求。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 4, requests = [[0,3],[3,1],[1,2],[2,0]]
<strong>输出：</strong>4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>1 &lt;= requests.length &lt;= 16</code></li>
	<li><code>requests[i].length == 2</code></li>
	<li><code>0 &lt;= from<sub>i</sub>, to<sub>i</sub> &lt; n</code></li>
</ul>

## 解法

### 方法一：二进制枚举

我们注意到，换楼请求列表长度不超过 $16$，因此我们可以使用二进制枚举的方法枚举所有的换楼请求列表。具体地，我们可以使用一个长度为 $16$ 的二进制数来表示一种换楼请求列表，其中第 $i$ 位为 $1$ 表示第 $i$ 个换楼请求被选中，为 $0$ 表示第 $i$ 个换楼请求不被选中。

我们在 $[1, 2^{m})$ 的范围内枚举所有的二进制数，对于每个二进制数 $mask$，我们先算出它的二进制表示中有多少个 $1$，记为 $cnt$，如果 $cnt$ 比当前答案 $ans$ 大，那么我们再判断 $mask$ 是否是一个可行的换楼请求列表。如果是，那么我们就用 $cnt$ 更新答案 $ans$。判断 $mask$ 是否是一个可行的换楼请求列表，只需要判断对于每个楼，它的净流入量是否为 $0$ 即可。

时间复杂度 $O(2^m \times (m + n))$，空间复杂度 $O(n)$。其中 $m$ 和 $n$ 分别是换楼请求列表的长度和楼的数量。

<!-- tabs:start -->

```python
class Solution:
    def maximumRequests(self, n: int, requests: List[List[int]]) -> int:
        def check(mask: int) -> bool:
            cnt = [0] * n
            for i, (f, t) in enumerate(requests):
                if mask >> i & 1:
                    cnt[f] -= 1
                    cnt[t] += 1
            return all(v == 0 for v in cnt)

        ans = 0
        for mask in range(1 << len(requests)):
            cnt = mask.bit_count()
            if ans < cnt and check(mask):
                ans = cnt
        return ans
```

```java
class Solution {
    private int m;
    private int n;
    private int[][] requests;

    public int maximumRequests(int n, int[][] requests) {
        m = requests.length;
        this.n = n;
        this.requests = requests;
        int ans = 0;
        for (int mask = 0; mask < 1 << m; ++mask) {
            int cnt = Integer.bitCount(mask);
            if (ans < cnt && check(mask)) {
                ans = cnt;
            }
        }
        return ans;
    }

    private boolean check(int mask) {
        int[] cnt = new int[n];
        for (int i = 0; i < m; ++i) {
            if ((mask >> i & 1) == 1) {
                int f = requests[i][0], t = requests[i][1];
                --cnt[f];
                ++cnt[t];
            }
        }
        for (int v : cnt) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    int maximumRequests(int n, vector<vector<int>>& requests) {
        int m = requests.size();
        int ans = 0;
        auto check = [&](int mask) -> bool {
            int cnt[n];
            memset(cnt, 0, sizeof(cnt));
            for (int i = 0; i < m; ++i) {
                if (mask >> i & 1) {
                    int f = requests[i][0], t = requests[i][1];
                    --cnt[f];
                    ++cnt[t];
                }
            }
            for (int v : cnt) {
                if (v) {
                    return false;
                }
            }
            return true;
        };
        for (int mask = 0; mask < 1 << m; ++mask) {
            int cnt = __builtin_popcount(mask);
            if (ans < cnt && check(mask)) {
                ans = cnt;
            }
        }
        return ans;
    }
};
```

```go
func maximumRequests(n int, requests [][]int) (ans int) {
	m := len(requests)
	check := func(mask int) bool {
		cnt := make([]int, n)
		for i, r := range requests {
			if mask>>i&1 == 1 {
				f, t := r[0], r[1]
				cnt[f]--
				cnt[t]++
			}
		}
		for _, v := range cnt {
			if v != 0 {
				return false
			}
		}
		return true
	}
	for mask := 0; mask < 1<<m; mask++ {
		cnt := bits.OnesCount(uint(mask))
		if ans < cnt && check(mask) {
			ans = cnt
		}
	}
	return
}
```

```ts
function maximumRequests(n: number, requests: number[][]): number {
    const m = requests.length;
    let ans = 0;
    const check = (mask: number): boolean => {
        const cnt = Array(n).fill(0);
        for (let i = 0; i < m; ++i) {
            if ((mask >> i) & 1) {
                const [f, t] = requests[i];
                --cnt[f];
                ++cnt[t];
            }
        }
        return cnt.every(v => v === 0);
    };
    for (let mask = 0; mask < 1 << m; ++mask) {
        const cnt = bitCount(mask);
        if (ans < cnt && check(mask)) {
            ans = cnt;
        }
    }
    return ans;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

```js
/**
 * @param {number} n
 * @param {number[][]} requests
 * @return {number}
 */
var maximumRequests = function (n, requests) {
    const m = requests.length;
    let ans = 0;
    const check = mask => {
        const cnt = new Array(n).fill(0);
        for (let i = 0; i < m; ++i) {
            if ((mask >> i) & 1) {
                const [f, t] = requests[i];
                --cnt[f];
                ++cnt[t];
            }
        }
        return cnt.every(v => v === 0);
    };
    for (let mask = 0; mask < 1 << m; ++mask) {
        const cnt = bitCount(mask);
        if (ans < cnt && check(mask)) {
            ans = cnt;
        }
    }
    return ans;
};

function bitCount(i) {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

```cs
public class Solution {
    private int m;
    private int n;
    private int[][] requests;

    public int MaximumRequests(int n, int[][] requests) {
        m = requests.Length;
        this.n = n;
        this.requests = requests;
        int ans = 0;
        for (int mask = 0; mask < (1 << m); ++mask) {
            int cnt = CountBits(mask);
            if (ans < cnt && Check(mask)) {
                ans = cnt;
            }
        }
        return ans;
    }

    private bool Check(int mask) {
        int[] cnt = new int[n];
        for (int i = 0; i < m; ++i) {
            if (((mask >> i) & 1) == 1) {
                int f = requests[i][0], t = requests[i][1];
                --cnt[f];
                ++cnt[t];
            }
        }
        foreach (int v in cnt) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }

    private int CountBits(int n) {
        int count = 0;
        while (n > 0) {
            n -= n & -n;
            ++count;
        }
        return count;
    }
}
```

<!-- tabs:end -->

<!-- end -->
