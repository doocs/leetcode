# [473. Matchsticks to Square](https://leetcode.com/problems/matchsticks-to-square)

[中文文档](/solution/0400-0499/0473.Matchsticks%20to%20Square/README.md)

## Description

<p>You are given an integer array <code>matchsticks</code> where <code>matchsticks[i]</code> is the length of the <code>i<sup>th</sup></code> matchstick. You want to use <strong>all the matchsticks</strong> to make one square. You <strong>should not break</strong> any stick, but you can link them up, and each matchstick must be used <strong>exactly one time</strong>.</p>

<p>Return <code>true</code> if you can make this square and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0473.Matchsticks%20to%20Square/images/matchsticks1-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>Input:</strong> matchsticks = [1,1,2,2,2]
<strong>Output:</strong> true
<strong>Explanation:</strong> You can form a square with length 2, one side of the square came two sticks with length 1.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> matchsticks = [3,3,3,3,4]
<strong>Output:</strong> false
<strong>Explanation:</strong> You cannot find a way to form a square with all the matchsticks.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= matchsticks.length &lt;= 15</code></li>
	<li><code>1 &lt;= matchsticks[i] &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def makesquare(self, matchsticks: List[int]) -> bool:
        def dfs(u):
            if u == len(matchsticks):
                return True
            for i in range(4):
                if i > 0 and edges[i - 1] == edges[i]:
                    continue
                edges[i] += matchsticks[u]
                if edges[i] <= x and dfs(u + 1):
                    return True
                edges[i] -= matchsticks[u]
            return False

        x, mod = divmod(sum(matchsticks), 4)
        if mod or x < max(matchsticks):
            return False
        edges = [0] * 4
        matchsticks.sort(reverse=True)
        return dfs(0)
```

```python
class Solution:
    def makesquare(self, matchsticks: List[int]) -> bool:
        @cache
        def dfs(state, t):
            if state == (1 << len(matchsticks)) - 1:
                return True
            for i, v in enumerate(matchsticks):
                if (state & (1 << i)):
                    continue
                if t + v > s:
                    break
                if dfs(state | (1 << i), (t + v) % s):
                    return True
            return False

        s, mod = divmod(sum(matchsticks), 4)
        matchsticks.sort()
        if mod:
            return False
        return dfs(0, 0)
```

### **Java**

```java
class Solution {
    public boolean makesquare(int[] matchsticks) {
        int s = 0, mx = 0;
        for (int v : matchsticks) {
            s += v;
            mx = Math.max(mx, v);
        }
        int x = s / 4, mod = s % 4;
        if (mod != 0 || x < mx) {
            return false;
        }
        Arrays.sort(matchsticks);
        int[] edges = new int[4];
        return dfs(matchsticks.length - 1, x, matchsticks, edges);
    }

    private boolean dfs(int u, int x, int[] matchsticks, int[] edges) {
        if (u < 0) {
            return true;
        }
        for (int i = 0; i < 4; ++i) {
            if (i > 0 && edges[i - 1] == edges[i]) {
                continue;
            }
            edges[i] += matchsticks[u];
            if (edges[i] <= x && dfs(u - 1, x, matchsticks, edges)) {
                return true;
            }
            edges[i] -= matchsticks[u];
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool makesquare(vector<int>& matchsticks) {
        int s = 0, mx = 0;
        for (int& v : matchsticks) {
            s += v;
            mx = max(mx, v);
        }
        int x = s / 4, mod = s % 4;
        if (mod != 0 || x < mx) return false;
        sort(matchsticks.begin(), matchsticks.end(), greater<int>());
        vector<int> edges(4);
        return dfs(0, x, matchsticks, edges);
    }

    bool dfs(int u, int x, vector<int>& matchsticks, vector<int>& edges) {
        if (u == matchsticks.size()) return true;
        for (int i = 0; i < 4; ++i) {
            if (i > 0 && edges[i - 1] == edges[i]) continue;
            edges[i] += matchsticks[u];
            if (edges[i] <= x && dfs(u + 1, x, matchsticks, edges)) return true;
            edges[i] -= matchsticks[u];
        }
        return false;
    }
};
```

### **Go**

```go
func makesquare(matchsticks []int) bool {
	s := 0
	for _, v := range matchsticks {
		s += v
	}
	if s%4 != 0 {
		return false
	}
	sort.Sort(sort.Reverse(sort.IntSlice(matchsticks)))
	edges := make([]int, 4)
	var dfs func(u, x int) bool
	dfs = func(u, x int) bool {
		if u == len(matchsticks) {
			return true
		}
		for i := 0; i < 4; i++ {
			if i > 0 && edges[i-1] == edges[i] {
				continue
			}
			edges[i] += matchsticks[u]
			if edges[i] <= x && dfs(u+1, x) {
				return true
			}
			edges[i] -= matchsticks[u]
		}
		return false
	}
	return dfs(0, s/4)
}
```

### **Rust**

```rust
impl Solution {
    pub fn makesquare(matchsticks: Vec<i32>) -> bool {
        let mut matchsticks = matchsticks;

        fn dfs(matchsticks: &Vec<i32>, edges: &mut [i32; 4], u: usize, x: i32) -> bool {
            if u == matchsticks.len() {
                return true;
            }
            for i in 0..4 {
                if i > 0 && edges[i - 1] == edges[i] {
                    continue;
                }
                edges[i] += matchsticks[u];
                if edges[i] <= x && dfs(matchsticks, edges, u + 1, x) {
                    return true;
                }
                edges[i] -= matchsticks[u];
            }
            false
        }

        let sum: i32 = matchsticks.iter().sum();
        if sum % 4 != 0 {
            return false;
        }
        matchsticks.sort_by(|x, y| y.cmp(x));
        let mut edges = [0; 4];

        dfs(&matchsticks, &mut edges, 0, sum / 4)
    }
}
```

### **...**

```

```

<!-- tabs:end -->
