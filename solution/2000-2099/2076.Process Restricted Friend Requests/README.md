# [2076. 处理含限制条件的好友请求](https://leetcode.cn/problems/process-restricted-friend-requests)

[English Version](/solution/2000-2099/2076.Process%20Restricted%20Friend%20Requests/README_EN.md)

<!-- tags:并查集,图 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code> ，表示网络上的用户数目。每个用户按从 <code>0</code> 到 <code>n - 1</code> 进行编号。</p>

<p>给你一个下标从 <strong>0</strong> 开始的二维整数数组 <code>restrictions</code> ，其中 <code>restrictions[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 意味着用户 <code>x<sub>i</sub></code> 和用户 <code>y<sub>i</sub></code> <strong>不能</strong> 成为 <strong>朋友</strong> ，不管是 <strong>直接</strong> 还是通过其他用户 <strong>间接</strong> 。</p>

<p>最初，用户里没有人是其他用户的朋友。给你一个下标从 <strong>0</strong> 开始的二维整数数组 <code>requests</code> 表示好友请求的列表，其中 <code>requests[j] = [u<sub>j</sub>, v<sub>j</sub>]</code> 是用户 <code>u<sub>j</sub></code> 和用户 <code>v<sub>j</sub></code> 之间的一条好友请求。</p>

<p>如果 <code>u<sub>j</sub></code> 和 <code>v<sub>j</sub></code> 可以成为 <strong>朋友</strong> ，那么好友请求将会 <strong>成功</strong> 。每个好友请求都会按列表中给出的顺序进行处理（即，<code>requests[j]</code> 会在 <code>requests[j + 1]</code> 前）。一旦请求成功，那么对所有未来的好友请求而言， <code>u<sub>j</sub></code> 和 <code>v<sub>j</sub></code> 将会 <strong>成为直接朋友 。</strong></p>

<p>返回一个 <strong>布尔数组</strong> <code>result</code> ，其中元素遵循此规则：如果第 <code>j</code> 个好友请求 <strong>成功</strong><em> </em>，那么 <code>result[j]</code><em> </em>就是<em> </em><code>true</code><em> </em>；否则，为<em> </em><code>false</code> 。</p>

<p><strong>注意：</strong>如果 <code>u<sub>j</sub></code> 和 <code>v<sub>j</sub></code> 已经是直接朋友，那么他们之间的请求将仍然&nbsp;<strong>成功</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3, restrictions = [[0,1]], requests = [[0,2],[2,1]]
<strong>输出：</strong>[true,false]
<strong>解释：
</strong>请求 0 ：用户 0 和 用户 2 可以成为朋友，所以他们成为直接朋友。 
请求 1 ：用户 2 和 用户 1 不能成为朋友，因为这会使 用户 0 和 用户 1 成为间接朋友 (1--2--0) 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3, restrictions = [[0,1]], requests = [[1,2],[0,2]]
<strong>输出：</strong>[true,false]
<strong>解释：</strong>
请求 0 ：用户 1 和 用户 2 可以成为朋友，所以他们成为直接朋友。 
请求 1 ：用户 0 和 用户 2 不能成为朋友，因为这会使 用户 0 和 用户 1 成为间接朋友 (0--2--1) 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 5, restrictions = [[0,1],[1,2],[2,3]], requests = [[0,4],[1,2],[3,1],[3,4]]
<strong>输出：</strong>[true,false,true,false]
<strong>解释：
</strong>请求 0 ：用户 0 和 用户 4 可以成为朋友，所以他们成为直接朋友。 
请求 1 ：用户 1 和 用户 2 不能成为朋友，因为他们之间存在限制。
请求 2 ：用户 3 和 用户 1 可以成为朋友，所以他们成为直接朋友。 
请求 3 ：用户 3 和 用户 4 不能成为朋友，因为这会使 用户 0 和 用户 1 成为间接朋友 (0--4--3--1) 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= restrictions.length &lt;= 1000</code></li>
	<li><code>restrictions[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>x<sub>i</sub> != y<sub>i</sub></code></li>
	<li><code>1 &lt;= requests.length &lt;= 1000</code></li>
	<li><code>requests[j].length == 2</code></li>
	<li><code>0 &lt;= u<sub>j</sub>, v<sub>j</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>j</sub> != v<sub>j</sub></code></li>
</ul>

## 解法

### 方法一：并查集

我们可以用并查集来维护朋友关系，然后对于每个请求，判断是否满足限制条件。

对于当前请求的两个人 $(u, v)$，如果他们已经是朋友，那么可以直接接受请求；否则，我们遍历限制条件，如果存在限制条件 $(x, y)$，使得 $u$ 和 $x$ 互为朋友并且 $v$ 和 $y$ 互为朋友，或者 $u$ 和 $y$ 互为朋友并且 $v$ 和 $x$ 互为朋友，那么就不能接受请求。

时间复杂度 $O(q \times m \times \log(n))$，空间复杂度 $O(n)$。其中 $q$ 和 $m$ 分别是请求的数量和限制条件的数量。

<!-- tabs:start -->

```python
class Solution:
    def friendRequests(
        self, n: int, restrictions: List[List[int]], requests: List[List[int]]
    ) -> List[bool]:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(n))
        ans = []
        for u, v in requests:
            pu, pv = find(u), find(v)
            if pu == pv:
                ans.append(True)
            else:
                ok = True
                for x, y in restrictions:
                    px, py = find(x), find(y)
                    if (pu == px and pv == py) or (pu == py and pv == px):
                        ok = False
                        break
                ans.append(ok)
                if ok:
                    p[pu] = pv
        return ans
```

```java
class Solution {
    private int[] p;

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        int m = requests.length;
        boolean[] ans = new boolean[m];
        for (int i = 0; i < m; ++i) {
            int u = requests[i][0], v = requests[i][1];
            int pu = find(u), pv = find(v);
            if (pu == pv) {
                ans[i] = true;
            } else {
                boolean ok = true;
                for (var r : restrictions) {
                    int px = find(r[0]), py = find(r[1]);
                    if ((pu == px && pv == py) || (pu == py && pv == px)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    ans[i] = true;
                    p[pu] = pv;
                }
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

```cpp
class Solution {
public:
    vector<bool> friendRequests(int n, vector<vector<int>>& restrictions, vector<vector<int>>& requests) {
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        function<int(int)> find = [&](int x) {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        vector<bool> ans;
        for (auto& req : requests) {
            int u = req[0], v = req[1];
            int pu = find(u), pv = find(v);
            if (pu == pv) {
                ans.push_back(true);
            } else {
                bool ok = true;
                for (auto& r : restrictions) {
                    int px = find(r[0]), py = find(r[1]);
                    if ((pu == px && pv == py) || (pu == py && pv == px)) {
                        ok = false;
                        break;
                    }
                }
                ans.push_back(ok);
                if (ok) {
                    p[pu] = pv;
                }
            }
        }
        return ans;
    }
};
```

```go
func friendRequests(n int, restrictions [][]int, requests [][]int) (ans []bool) {
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, req := range requests {
		pu, pv := find(req[0]), find(req[1])
		if pu == pv {
			ans = append(ans, true)
		} else {
			ok := true
			for _, r := range restrictions {
				px, py := find(r[0]), find(r[1])
				if px == pu && py == pv || px == pv && py == pu {
					ok = false
					break
				}
			}
			ans = append(ans, ok)
			if ok {
				p[pv] = pu
			}
		}
	}
	return
}
```

```ts
function friendRequests(n: number, restrictions: number[][], requests: number[][]): boolean[] {
    const p: number[] = Array.from({ length: n }, (_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    const ans: boolean[] = [];
    for (const [u, v] of requests) {
        const pu = find(u);
        const pv = find(v);
        if (pu === pv) {
            ans.push(true);
        } else {
            let ok = true;
            for (const [x, y] of restrictions) {
                const px = find(x);
                const py = find(y);
                if ((px === pu && py === pv) || (px === pv && py === pu)) {
                    ok = false;
                    break;
                }
            }
            ans.push(ok);
            if (ok) {
                p[pu] = pv;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
