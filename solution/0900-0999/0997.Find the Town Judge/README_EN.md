# [997. Find the Town Judge](https://leetcode.com/problems/find-the-town-judge)

[中文文档](/solution/0900-0999/0997.Find%20the%20Town%20Judge/README.md)

## Description

<p>In a town, there are <code>n</code> people labeled from <code>1</code> to <code>n</code>. There is a rumor that one of these people is secretly the town judge.</p>

<p>If the town judge exists, then:</p>

<ol>
	<li>The town judge trusts nobody.</li>
	<li>Everybody (except for the town judge) trusts the town judge.</li>
	<li>There is exactly one person that satisfies properties <strong>1</strong> and <strong>2</strong>.</li>
</ol>

<p>You are given an array <code>trust</code> where <code>trust[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> representing that the person labeled <code>a<sub>i</sub></code> trusts the person labeled <code>b<sub>i</sub></code>.</p>

<p>Return <em>the label of the town judge if the town judge exists and can be identified, or return </em><code>-1</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2, trust = [[1,2]]
<strong>Output:</strong> 2
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, trust = [[1,3],[2,3]]
<strong>Output:</strong> 3
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3, trust = [[1,3],[2,3],[3,1]]
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= trust.length &lt;= 10<sup>4</sup></code></li>
	<li><code>trust[i].length == 2</code></li>
	<li>All the pairs of <code>trust</code> are <strong>unique</strong>.</li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findJudge(self, n: int, trust: List[List[int]]) -> int:
        N = 1001
        c1, c2 = [0] * N, [0] * N
        for a, b in trust:
            c1[a] += 1
            c2[b] += 1
        for i in range(1, N):
            if c1[i] == 0 and c2[i] == n - 1:
                return i
        return -1
```

### **Java**

```java
class Solution {
    public int findJudge(int n, int[][] trust) {
        int N = 1001;
        int[] c1 = new int[N];
        int[] c2 = new int[N];
        for (int[] e : trust) {
            ++c1[e[0]];
            ++c2[e[1]];
        }
        for (int i = 1; i < N; ++i) {
            if (c1[i] == 0 && c2[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
}
```

### **TypeScript**

```ts
function findJudge(n: number, trust: number[][]): number {
    let candidates = new Array(n).fill(0);
    for (let [a, b] of trust) {
        candidates[a - 1] = -1;
        if (candidates[b - 1] >= 0) {
            candidates[b - 1]++;
        }
    }

    for (let i = 0; i < n; i++) {
        if (candidates[i] == n - 1) {
            return i + 1;
        }
    }
    return -1;
}
```

### **C++**

```cpp
class Solution {
public:
    int findJudge(int n, vector<vector<int>>& trust) {
        int N = 1001;
        vector<int> c1(N);
        vector<int> c2(N);
        for (auto& e : trust) {
            ++c1[e[0]];
            ++c2[e[1]];
        }
        for (int i = 1; i < N; ++i) {
            if (c1[i] == 0 && c2[i] == n - 1) return i;
        }
        return -1;
    }
};
```

### **Go**

```go
func findJudge(n int, trust [][]int) int {
	N := 1001
	c1 := make([]int, N)
	c2 := make([]int, N)
	for _, e := range trust {
		c1[e[0]]++
		c2[e[1]]++
	}
	for i := 1; i < N; i++ {
		if c1[i] == 0 && c2[i] == n-1 {
			return i
		}
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
