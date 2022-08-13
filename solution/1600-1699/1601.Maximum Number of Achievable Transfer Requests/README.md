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

<!-- 这里可写通用的实现逻辑 -->

**方法一：二进制枚举**

二进制枚举所有方案，找出满足条件的最大请求数方案即可。

时间复杂度 O(m\*2^m)，其中 m 表示 requests 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumRequests(self, n: int, requests: List[List[int]]) -> int:
        def check(x):
            d = [0] * n
            for i, (f, t) in enumerate(requests):
                if (x >> i) & 1:
                    d[f] -= 1
                    d[t] += 1
            return all(v == 0 for v in d)

        ans, m = 0, len(requests)
        for mask in range(1 << m):
            cnt = mask.bit_count()
            if cnt > ans and check(mask):
                ans = cnt
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumRequests(int n, int[][] requests) {
        int ans = 0;
        for (int mask = 1; mask < 1 << requests.length; ++mask) {
            int cnt = Integer.bitCount(mask);
            if (ans < cnt && check(mask, requests)) {
                ans = cnt;
            }
        }
        return ans;
    }

    private boolean check(int x, int[][] requests) {
        int[] d = new int[21];
        for (int i = 0; i < requests.length; ++i) {
            if (((x >> i) & 1) == 1) {
                int f = requests[i][0];
                int t = requests[i][1];
                --d[f];
                ++d[t];
            }
        }
        for (int v : d) {
            if (v != 0) {
                return false;
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
    int maximumRequests(int n, vector<vector<int>>& requests) {
        int ans = 0, m = requests.size();
        for (int mask = 0; mask < 1 << m; ++mask) {
            int cnt = __builtin_popcount(mask);
            if (ans < cnt && check(mask, requests)) ans = cnt;
        }
        return ans;
    }

    bool check(int x, vector<vector<int>>& requests) {
        vector<int> d(21);
        for (int i = 0; i < requests.size(); ++i) {
            if ((x >> i) & 1) {
                --d[requests[i][0]];
                ++d[requests[i][1]];
            }
        }
        for (int& v : d)
            if (v) return 0;
        return 1;
    }
};
```

### **Go**

```go
func maximumRequests(n int, requests [][]int) int {
	check := func(x int) bool {
		d := make([]int, n)
		for i, r := range requests {
			if (x>>i)&1 == 1 {
				d[r[0]]--
				d[r[1]]++
			}
		}
		for _, v := range d {
			if v != 0 {
				return false
			}
		}
		return true
	}

	ans, m := 0, len(requests)
	for mask := 0; mask < 1<<m; mask++ {
		cnt := bits.OnesCount(uint(mask))
		if ans < cnt && check(mask) {
			ans = cnt
		}
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @param {number[][]} requests
 * @return {number}
 */
var maximumRequests = function (n, requests) {
    function check(x) {
        let d = new Array(n).fill(0);
        for (let i = 0; i < m; ++i) {
            if ((x >> i) & 1) {
                const [f, t] = requests[i];
                d[f]--;
                d[t]++;
            }
        }
        for (const v of d) {
            if (v) {
                return false;
            }
        }
        return true;
    }
    let ans = 0;
    let m = requests.length;
    for (let mask = 1; mask < 1 << m; ++mask) {
        let cnt = mask.toString(2).split('0').join('').length;
        if (ans < cnt && check(mask)) {
            ans = cnt;
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
