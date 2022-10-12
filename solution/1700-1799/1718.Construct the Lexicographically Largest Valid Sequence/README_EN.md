# [1718. Construct the Lexicographically Largest Valid Sequence](https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence)

[中文文档](/solution/1700-1799/1718.Construct%20the%20Lexicographically%20Largest%20Valid%20Sequence/README.md)

## Description

<p>Given an integer <code>n</code>, find a sequence that satisfies all of the following:</p>

<ul>
	<li>The integer <code>1</code> occurs once in the sequence.</li>
	<li>Each integer between <code>2</code> and <code>n</code> occurs twice in the sequence.</li>
	<li>For every integer <code>i</code> between <code>2</code> and <code>n</code>, the <strong>distance</strong> between the two occurrences of <code>i</code> is exactly <code>i</code>.</li>
</ul>

<p>The <strong>distance</strong> between two numbers on the sequence, <code>a[i]</code> and <code>a[j]</code>, is the absolute difference of their indices, <code>|j - i|</code>.</p>

<p>Return <em>the <strong>lexicographically largest</strong> sequence</em><em>. It is guaranteed that under the given constraints, there is always a solution. </em></p>

<p>A sequence <code>a</code> is lexicographically larger than a sequence <code>b</code> (of the same length) if in the first position where <code>a</code> and <code>b</code> differ, sequence <code>a</code> has a number greater than the corresponding number in <code>b</code>. For example, <code>[0,1,9,0]</code> is lexicographically larger than <code>[0,1,5,6]</code> because the first position they differ is at the third number, and <code>9</code> is greater than <code>5</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> [3,1,2,3,2]
<strong>Explanation:</strong> [2,3,2,1,3] is also a valid sequence, but [3,1,2,3,2] is the lexicographically largest valid sequence.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> [5,3,1,4,3,5,2,4,2]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 20</code></li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def constructDistancedSequence(self, n: int) -> List[int]:
        def dfs(u):
            if u == n * 2:
                return True
            if path[u]:
                return dfs(u + 1)
            for i in range(n, 1, -1):
                if cnt[i] and u + i < n * 2 and path[u + i] == 0:
                    cnt[i] = 0
                    path[u] = path[u + i] = i
                    if dfs(u + 1):
                        return True
                    path[u] = path[u + i] = 0
                    cnt[i] = 2
            if cnt[1]:
                cnt[1], path[u] = 0, 1
                if dfs(u + 1):
                    return True
                path[u], cnt[1] = 0, 1
            return False

        path = [0] * (n * 2)
        cnt = [2] * (n * 2)
        cnt[1] = 1
        dfs(1)
        return path[1:]
```

### **Java**

```java
class Solution {
    private int[] path;
    private int[] cnt;
    private int n;

    public int[] constructDistancedSequence(int n) {
        this.n = n;
        path = new int[n * 2];
        cnt = new int[n * 2];
        Arrays.fill(cnt, 2);
        cnt[1] = 1;
        dfs(1);
        int[] ans = new int[n * 2 - 1];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = path[i + 1];
        }
        return ans;
    }

    private boolean dfs(int u) {
        if (u == n * 2) {
            return true;
        }
        if (path[u] > 0) {
            return dfs(u + 1);
        }
        for (int i = n; i > 1; --i) {
            if (cnt[i] > 0 && u + i < n * 2 && path[u + i] == 0) {
                cnt[i] = 0;
                path[u] = i;
                path[u + i] = i;
                if (dfs(u + 1)) {
                    return true;
                }
                cnt[i] = 2;
                path[u] = 0;
                path[u + i] = 0;
            }
        }
        if (cnt[1] > 0) {
            path[u] = 1;
            cnt[1] = 0;
            if (dfs(u + 1)) {
                return true;
            }
            cnt[1] = 1;
            path[u] = 0;
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int n;
    vector<int> cnt, path;

    vector<int> constructDistancedSequence(int _n) {
        n = _n;
        cnt.resize(n * 2, 2);
        path.resize(n * 2);
        cnt[1] = 1;
        dfs(1);
        vector<int> ans;
        for (int i = 1; i < n * 2; ++i) ans.push_back(path[i]);
        return ans;
    }

    bool dfs(int u) {
        if (u == n * 2) return 1;
        if (path[u]) return dfs(u + 1);
        for (int i = n; i > 1; --i) {
            if (cnt[i] && u + i < n * 2 && !path[u + i]) {
                path[u] = path[u + i] = i;
                cnt[i] = 0;
                if (dfs(u + 1)) return 1;
                cnt[i] = 2;
                path[u] = path[u + i] = 0;
            }
        }
        if (cnt[1]) {
            path[u] = 1;
            cnt[1] = 0;
            if (dfs(u + 1)) return 1;
            cnt[1] = 1;
            path[u] = 0;
        }
        return 0;
    }
};
```

### **Go**

```go
func constructDistancedSequence(n int) []int {
	path := make([]int, n*2)
	cnt := make([]int, n*2)
	for i := range cnt {
		cnt[i] = 2
	}
	cnt[1] = 1
	var dfs func(u int) bool
	dfs = func(u int) bool {
		if u == n*2 {
			return true
		}
		if path[u] > 0 {
			return dfs(u + 1)
		}
		for i := n; i > 1; i-- {
			if cnt[i] > 0 && u+i < n*2 && path[u+i] == 0 {
				cnt[i] = 0
				path[u], path[u+i] = i, i
				if dfs(u + 1) {
					return true
				}
				cnt[i] = 2
				path[u], path[u+i] = 0, 0
			}
		}
		if cnt[1] > 0 {
			cnt[1] = 0
			path[u] = 1
			if dfs(u + 1) {
				return true
			}
			cnt[1] = 1
			path[u] = 0
		}
		return false
	}
	dfs(1)
	return path[1:]
}
```

### **...**

```

```

<!-- tabs:end -->
