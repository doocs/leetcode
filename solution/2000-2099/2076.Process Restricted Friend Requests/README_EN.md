# [2076. Process Restricted Friend Requests](https://leetcode.com/problems/process-restricted-friend-requests)

[中文文档](/solution/2000-2099/2076.Process%20Restricted%20Friend%20Requests/README.md)

## Description

<p>You are given an integer <code>n</code> indicating the number of people in a network. Each person is labeled from <code>0</code> to <code>n - 1</code>.</p>

<p>You are also given a <strong>0-indexed</strong> 2D integer array <code>restrictions</code>, where <code>restrictions[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> means that person <code>x<sub>i</sub></code> and person <code>y<sub>i</sub></code> <strong>cannot </strong>become <strong>friends</strong>,<strong> </strong>either <strong>directly</strong> or <strong>indirectly</strong> through other people.</p>

<p>Initially, no one is friends with each other. You are given a list of friend requests as a <strong>0-indexed</strong> 2D integer array <code>requests</code>, where <code>requests[j] = [u<sub>j</sub>, v<sub>j</sub>]</code> is a friend request between person <code>u<sub>j</sub></code> and person <code>v<sub>j</sub></code>.</p>

<p>A friend request is <strong>successful </strong>if <code>u<sub>j</sub></code> and <code>v<sub>j</sub></code> can be <strong>friends</strong>. Each friend request is processed in the given order (i.e., <code>requests[j]</code> occurs before <code>requests[j + 1]</code>), and upon a successful request, <code>u<sub>j</sub></code> and <code>v<sub>j</sub></code> <strong>become direct friends</strong> for all future friend requests.</p>

<p>Return <em>a <strong>boolean array</strong> </em><code>result</code>,<em> where each </em><code>result[j]</code><em> is </em><code>true</code><em> if the </em><code>j<sup>th</sup></code><em> friend request is <strong>successful</strong> or </em><code>false</code><em> if it is not</em>.</p>

<p><strong>Note:</strong> If <code>u<sub>j</sub></code> and <code>v<sub>j</sub></code> are already direct friends, the request is still <strong>successful</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, restrictions = [[0,1]], requests = [[0,2],[2,1]]
<strong>Output:</strong> [true,false]
<strong>Explanation:
</strong>Request 0: Person 0 and person 2 can be friends, so they become direct friends. 
Request 1: Person 2 and person 1 cannot be friends since person 0 and person 1 would be indirect friends (1--2--0).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, restrictions = [[0,1]], requests = [[1,2],[0,2]]
<strong>Output:</strong> [true,false]
<strong>Explanation:
</strong>Request 0: Person 1 and person 2 can be friends, so they become direct friends.
Request 1: Person 0 and person 2 cannot be friends since person 0 and person 1 would be indirect friends (0--2--1).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 5, restrictions = [[0,1],[1,2],[2,3]], requests = [[0,4],[1,2],[3,1],[3,4]]
<strong>Output:</strong> [true,false,true,false]
<strong>Explanation:
</strong>Request 0: Person 0 and person 4 can be friends, so they become direct friends.
Request 1: Person 1 and person 2 cannot be friends since they are directly restricted.
Request 2: Person 3 and person 1 can be friends, so they become direct friends.
Request 3: Person 3 and person 4 cannot be friends since person 0 and person 1 would be indirect friends (0--4--3--1).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

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

## Solutions

<!-- tabs:start -->

### **Python3**

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
                    if ((find(u) == find(x) && find(v) == find(y))
                        || (find(u) == find(y) && find(v) == find(x))) {
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
