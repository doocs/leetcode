# [216. Combination Sum III](https://leetcode.com/problems/combination-sum-iii)

[中文文档](/solution/0200-0299/0216.Combination%20Sum%20III/README.md)

## Description

<p>Find all valid combinations of <code>k</code> numbers that sum up to <code>n</code> such that the following conditions are true:</p>

<ul>
	<li>Only numbers <code>1</code> through <code>9</code> are used.</li>
	<li>Each number is used <strong>at most once</strong>.</li>
</ul>

<p>Return <em>a list of all possible valid combinations</em>. The list must not contain the same combination twice, and the combinations may be returned in any order.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> k = 3, n = 7
<strong>Output:</strong> [[1,2,4]]
<strong>Explanation:</strong>
1 + 2 + 4 = 7
There are no other valid combinations.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> k = 3, n = 9
<strong>Output:</strong> [[1,2,6],[1,3,5],[2,3,4]]
<strong>Explanation:</strong>
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
There are no other valid combinations.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> k = 4, n = 1
<strong>Output:</strong> []
<strong>Explanation:</strong> There are no valid combinations.
Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 &gt; 1, there are no valid combination.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= k &lt;= 9</code></li>
	<li><code>1 &lt;= n &lt;= 60</code></li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def combinationSum3(self, k: int, n: int) -> List[List[int]]:
        def dfs(i, s, t):
            if i > 9 or s > n or len(t) > k:
                return
            if s == n and len(t) == k:
                ans.append(t[:])
                return
            i += 1
            t.append(i)
            dfs(i, s + i, t)
            t.pop()
            dfs(i, s, t)

        ans = []
        dfs(0, 0, [])
        return ans
```

### **Java**

```java
class Solution {
    private List<List<Integer>> ans;

    public List<List<Integer>> combinationSum3(int k, int n) {
        ans = new ArrayList<>();
        dfs(0, n, k, new ArrayList<>());
        return ans;
    }

    private void dfs(int i, int n, int k, List<Integer> t) {
        if (i > 9 || n < 0 || t.size() > k) {
            return;
        }
        if (n == 0 && t.size() == k) {
            ans.add(new ArrayList<>(t));
            return;
        }
        ++i;
        t.add(i);
        dfs(i, n - i, k, t);
        t.remove(t.size() - 1);
        dfs(i, n, k, t);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> ans;

    vector<vector<int>> combinationSum3(int k, int n) {
        vector<int> t;
        dfs(0, n, k, t);
        return ans;
    }

    void dfs(int i, int n, int k, vector<int>& t) {
        if (i > 9 || n < 0 || t.size() > k) return;
        if (n == 0 && t.size() == k) {
            ans.push_back(t);
            return;
        }
        ++i;
        t.push_back(i);
        dfs(i, n - i, k, t);
        t.pop_back();
        dfs(i, n, k, t);
    }
};
```

### **Go**

```go
func combinationSum3(k int, n int) [][]int {
	var ans [][]int
	var t []int
	var dfs func(i, n int, t []int)
	dfs = func(i, n int, t []int) {
		if i > 9 || n < 0 || len(t) > k {
			return
		}
		if n == 0 && len(t) == k {
			cp := make([]int, len(t))
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		i++
		t = append(t, i)
		dfs(i, n-i, t)
		t = t[:len(t)-1]
		dfs(i, n, t)
	}

	dfs(0, n, t)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
