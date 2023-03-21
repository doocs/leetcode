# [1340. Jump Game V](https://leetcode.com/problems/jump-game-v)

[中文文档](/solution/1300-1399/1340.Jump%20Game%20V/README.md)

## Description

<p>Given an array of&nbsp;integers <code>arr</code> and an integer <code>d</code>. In one step you can jump from index <code>i</code> to index:</p>

<ul>
	<li><code>i + x</code> where:&nbsp;<code>i + x &lt; arr.length</code> and <code> 0 &lt;&nbsp;x &lt;= d</code>.</li>
	<li><code>i - x</code> where:&nbsp;<code>i - x &gt;= 0</code> and <code> 0 &lt;&nbsp;x &lt;= d</code>.</li>
</ul>

<p>In addition, you can only jump from index <code>i</code> to index <code>j</code>&nbsp;if <code>arr[i] &gt; arr[j]</code> and <code>arr[i] &gt; arr[k]</code> for all indices <code>k</code> between <code>i</code> and <code>j</code> (More formally <code>min(i,&nbsp;j) &lt; k &lt; max(i, j)</code>).</p>

<p>You can choose any index of the array and start jumping. Return <em>the maximum number of indices</em>&nbsp;you can visit.</p>

<p>Notice that you can not jump outside of the array at any time.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1340.Jump%20Game%20V/images/meta-chart.jpeg" style="width: 633px; height: 419px;" />
<pre>
<strong>Input:</strong> arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> You can start at index 10. You can jump 10 --&gt; 8 --&gt; 6 --&gt; 7 as shown.
Note that if you start at index 6 you can only jump to index 7. You cannot jump to index 5 because 13 &gt; 9. You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 &gt; 9.
Similarly You cannot jump from index 3 to index 2 or index 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,3,3,3,3], d = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can start at any index. You always cannot jump to any index.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [7,6,5,4,3,2,1], d = 1
<strong>Output:</strong> 7
<strong>Explanation:</strong> Start at index 0. You can visit all the indicies. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= d &lt;= arr.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxJumps(self, arr: List[int], d: int) -> int:
        @cache
        def dfs(i):
            ans = 1
            for j in range(i - 1, -1, -1):
                if i - j > d or arr[j] >= arr[i]:
                    break
                ans = max(ans, 1 + dfs(j))
            for j in range(i + 1, n):
                if j - i > d or arr[j] >= arr[i]:
                    break
                ans = max(ans, 1 + dfs(j))
            return ans

        n = len(arr)
        return max(dfs(i) for i in range(n))
```

```python
class Solution:
    def maxJumps(self, arr: List[int], d: int) -> int:
        n = len(arr)
        f = [1] * n
        for x, i in sorted(zip(arr, range(n))):
            for j in range(i - 1, -1, -1):
                if i - j > d or arr[j] >= x:
                    break
                f[i] = max(f[i], 1 + f[j])
            for j in range(i + 1, n):
                if j - i > d or arr[j] >= x:
                    break
                f[i] = max(f[i], 1 + f[j])
        return max(f)
```

### **Java**

```java
class Solution {
    private int n;
    private int d;
    private int[] arr;
    private Integer[] f;

    public int maxJumps(int[] arr, int d) {
        n = arr.length;
        this.d = d;
        this.arr = arr;
        f = new Integer[n];
        int ans = 1;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, dfs(i));
        }
        return ans;
    }

    private int dfs(int i) {
        if (f[i] != null) {
            return f[i];
        }
        int ans = 1;
        for (int j = i - 1; j >= 0; --j) {
            if (i - j > d || arr[j] >= arr[i]) {
                break;
            }
            ans = Math.max(ans, 1 + dfs(j));
        }
        for (int j = i + 1; j < n; ++j) {
            if (j - i > d || arr[j] >= arr[i]) {
                break;
            }
            ans = Math.max(ans, 1 + dfs(j));
        }
        return f[i] = ans;
    }
}
```

```java
class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> arr[i] - arr[j]);
        int[] f = new int[n];
        Arrays.fill(f, 1);
        int ans = 0;
        for (int i : idx) {
            for (int j = i - 1; j >= 0; --j) {
                if (i - j > d || arr[j] >= arr[i]) {
                    break;
                }
                f[i] = Math.max(f[i], 1 + f[j]);
            }
            for (int j = i + 1; j < n; ++j) {
                if (j - i > d || arr[j] >= arr[i]) {
                    break;
                }
                f[i] = Math.max(f[i], 1 + f[j]);
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxJumps(vector<int>& arr, int d) {
        int n = arr.size();
        int f[n];
        memset(f, 0, sizeof(f));
        function<int(int)> dfs = [&](int i) -> int {
            if (f[i]) {
                return f[i];
            }
            int ans = 1;
            for (int j = i - 1; j >= 0; --j) {
                if (i - j > d || arr[j] >= arr[i]) {
                    break;
                }
                ans = max(ans, 1 + dfs(j));
            }
            for (int j = i + 1; j < n; ++j) {
                if (j - i > d || arr[j] >= arr[i]) {
                    break;
                }
                ans = max(ans, 1 + dfs(j));
            }
            return f[i] = ans;
        };
        int ans = 1;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, dfs(i));
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maxJumps(vector<int>& arr, int d) {
        int n = arr.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) { return arr[i] < arr[j]; });
        vector<int> f(n, 1);
        for (int i : idx) {
            for (int j = i - 1; j >= 0; --j) {
                if (i - j > d || arr[j] >= arr[i]) {
                    break;
                }
                f[i] = max(f[i], 1 + f[j]);
            }
            for (int j = i + 1; j < n; ++j) {
                if (j - i > d || arr[j] >= arr[i]) {
                    break;
                }
                f[i] = max(f[i], 1 + f[j]);
            }
        }
        return *max_element(f.begin(), f.end());
    }
};
```

### **Go**

```go
func maxJumps(arr []int, d int) (ans int) {
	n := len(arr)
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if f[i] != 0 {
			return f[i]
		}
		ans := 1
		for j := i - 1; j >= 0; j-- {
			if i-j > d || arr[j] >= arr[i] {
				break
			}
			ans = max(ans, 1+dfs(j))
		}
		for j := i + 1; j < n; j++ {
			if j-i > d || arr[j] >= arr[i] {
				break
			}
			ans = max(ans, 1+dfs(j))
		}
		f[i] = ans
		return ans
	}
	for i := 0; i < n; i++ {
		ans = max(ans, dfs(i))
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func maxJumps(arr []int, d int) (ans int) {
	n := len(arr)
	idx := make([]int, n)
	f := make([]int, n)
	for i := range f {
		idx[i] = i
		f[i] = 1
	}
	sort.Slice(idx, func(i, j int) bool { return arr[idx[i]] < arr[idx[j]] })
	for _, i := range idx {
		for j := i - 1; j >= 0; j-- {
			if i-j > d || arr[j] >= arr[i] {
				break
			}
			f[i] = max(f[i], 1+f[j])
		}
		for j := i + 1; j < n; j++ {
			if j-i > d || arr[j] >= arr[i] {
				break
			}
			f[i] = max(f[i], 1+f[j])
		}
		ans = max(ans, f[i])
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
