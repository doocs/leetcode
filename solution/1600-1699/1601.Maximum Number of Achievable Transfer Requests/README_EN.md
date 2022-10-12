# [1601. Maximum Number of Achievable Transfer Requests](https://leetcode.com/problems/maximum-number-of-achievable-transfer-requests)

[中文文档](/solution/1600-1699/1601.Maximum%20Number%20of%20Achievable%20Transfer%20Requests/README.md)

## Description

<p>We have <code>n</code> buildings numbered from <code>0</code> to <code>n - 1</code>. Each building has a number of employees. It&#39;s transfer season, and some employees want to change the building they reside in.</p>

<p>You are given an array <code>requests</code> where <code>requests[i] = [from<sub>i</sub>, to<sub>i</sub>]</code> represents an employee&#39;s request to transfer from building <code>from<sub>i</sub></code> to building <code>to<sub>i</sub></code>.</p>

<p><strong>All buildings are full</strong>, so a list of requests is achievable only if for each building, the <strong>net change in employee transfers is zero</strong>. This means the number of employees <strong>leaving</strong> is <strong>equal</strong> to the number of employees <strong>moving in</strong>. For example if <code>n = 3</code> and two employees are leaving building <code>0</code>, one is leaving building <code>1</code>, and one is leaving building <code>2</code>, there should be two employees moving to building <code>0</code>, one employee moving to building <code>1</code>, and one employee moving to building <code>2</code>.</p>

<p>Return <em>the maximum number of achievable requests</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1601.Maximum%20Number%20of%20Achievable%20Transfer%20Requests/images/move1.jpg" style="width: 600px; height: 406px;" />
<pre>
<strong>Input:</strong> n = 5, requests = [[0,1],[1,0],[0,1],[1,2],[2,0],[3,4]]
<strong>Output:</strong> 5
<strong>Explantion:</strong> Let&#39;s see the requests:
From building 0 we have employees x and y and both want to move to building 1.
From building 1 we have employees a and b and they want to move to buildings 2 and 0 respectively.
From building 2 we have employee z and they want to move to building 0.
From building 3 we have employee c and they want to move to building 4.
From building 4 we don&#39;t have any requests.
We can achieve the requests of users x and b by swapping their places.
We can achieve the requests of users y, a and z by swapping the places in the 3 buildings.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1601.Maximum%20Number%20of%20Achievable%20Transfer%20Requests/images/move2.jpg" style="width: 450px; height: 327px;" />
<pre>
<strong>Input:</strong> n = 3, requests = [[0,0],[1,2],[2,1]]
<strong>Output:</strong> 3
<strong>Explantion:</strong> Let&#39;s see the requests:
From building 0 we have employee x and they want to stay in the same building 0.
From building 1 we have employee y and they want to move to building 2.
From building 2 we have employee z and they want to move to building 1.
We can achieve all the requests. </pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 4, requests = [[0,3],[3,1],[1,2],[2,0]]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>1 &lt;= requests.length &lt;= 16</code></li>
	<li><code>requests[i].length == 2</code></li>
	<li><code>0 &lt;= from<sub>i</sub>, to<sub>i</sub> &lt; n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
