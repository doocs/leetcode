# [2076. 处理含限制条件的好友请求](https://leetcode.cn/problems/process-restricted-friend-requests)

[English Version](/solution/2000-2099/2076.Process%20Restricted%20Friend%20Requests/README_EN.md)

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

<!-- 这里可写通用的实现逻辑 -->

并查集。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def friendRequests(
        self, n: int, restrictions: List[List[int]], requests: List[List[int]]
    ) -> List[bool]:
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        ans = []
        i = 0
        for u, v in requests:
            if find(u) == find(v):
                ans.append(True)
            else:
                valid = True
                for x, y in restrictions:
                    if (find(u) == find(x) and find(v) == find(y)) or (
                        find(u) == find(y) and find(v) == find(x)
                    ):
                        valid = False
                        break
                ans.append(valid)
                if valid:
                    p[find(u)] = find(v)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        boolean[] ans = new boolean[requests.length];
        int i = 0;
        for (int[] req : requests) {
            int u = req[0], v = req[1];
            if (find(u) == find(v)) {
                ans[i++] = true;
            } else {
                boolean valid = true;
                for (int[] res : restrictions) {
                    int x = res[0], y = res[1];
                    if ((find(u) == find(x) && find(v) == find(y)) || (find(u) == find(y) && find(v) == find(x))) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    p[find(u)] = find(v);
                    ans[i++] = true;
                } else {
                    ans[i++] = false;
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

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    vector<bool> friendRequests(int n, vector<vector<int>>& restrictions, vector<vector<int>>& requests) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        vector<bool> ans;
        for (auto& req : requests) {
            int u = req[0], v = req[1];
            if (find(u) == find(v))
                ans.push_back(true);
            else {
                bool valid = true;
                for (auto& res : restrictions) {
                    int x = res[0], y = res[1];
                    if ((find(u) == find(x) && find(v) == find(y)) || (find(u) == find(y) && find(v) == find(x))) {
                        valid = false;
                        break;
                    }
                }
                ans.push_back(valid);
                if (valid) p[find(u)] = find(v);
            }
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
var p []int

func friendRequests(n int, restrictions [][]int, requests [][]int) []bool {
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	var ans []bool
	for _, req := range requests {
		u, v := req[0], req[1]
		if find(u) == find(v) {
			ans = append(ans, true)
		} else {
			valid := true
			for _, res := range restrictions {
				x, y := res[0], res[1]
				if (find(u) == find(x) && find(v) == find(y)) || (find(u) == find(y) && find(v) == find(x)) {
					valid = false
					break
				}
			}
			ans = append(ans, valid)
			if valid {
				p[find(u)] = find(v)
			}
		}
	}
	return ans
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}
```

### **...**

```

```

<!-- tabs:end -->
