# [1562. Find Latest Group of Size M](https://leetcode.com/problems/find-latest-group-of-size-m)

[中文文档](/solution/1500-1599/1562.Find%20Latest%20Group%20of%20Size%20M/README.md)

## Description

<p>Given an array <code>arr</code> that represents a permutation of numbers from <code>1</code> to <code>n</code>.</p>

<p>You have a binary string of size <code>n</code> that initially has all its bits set to zero. At each step <code>i</code> (assuming both the binary string and <code>arr</code> are 1-indexed) from <code>1</code> to <code>n</code>, the bit at position <code>arr[i]</code> is set to <code>1</code>.</p>

<p>You are also given an integer <code>m</code>. Find the latest step at which there exists a group of ones of length <code>m</code>. A group of ones is a contiguous substring of <code>1</code>&#39;s such that it cannot be extended in either direction.</p>

<p>Return <em>the latest step at which there exists a group of ones of length <strong>exactly</strong></em> <code>m</code>. <em>If no such group exists, return</em> <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,5,1,2,4], m = 1
<strong>Output:</strong> 4
<strong>Explanation:</strong> 
Step 1: &quot;00<u>1</u>00&quot;, groups: [&quot;1&quot;]
Step 2: &quot;0010<u>1</u>&quot;, groups: [&quot;1&quot;, &quot;1&quot;]
Step 3: &quot;<u>1</u>0101&quot;, groups: [&quot;1&quot;, &quot;1&quot;, &quot;1&quot;]
Step 4: &quot;1<u>1</u>101&quot;, groups: [&quot;111&quot;, &quot;1&quot;]
Step 5: &quot;111<u>1</u>1&quot;, groups: [&quot;11111&quot;]
The latest step at which there exists a group of size 1 is step 4.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,1,5,4,2], m = 2
<strong>Output:</strong> -1
<strong>Explanation:</strong> 
Step 1: &quot;00<u>1</u>00&quot;, groups: [&quot;1&quot;]
Step 2: &quot;<u>1</u>0100&quot;, groups: [&quot;1&quot;, &quot;1&quot;]
Step 3: &quot;1010<u>1</u>&quot;, groups: [&quot;1&quot;, &quot;1&quot;, &quot;1&quot;]
Step 4: &quot;101<u>1</u>1&quot;, groups: [&quot;1&quot;, &quot;111&quot;]
Step 5: &quot;1<u>1</u>111&quot;, groups: [&quot;11111&quot;]
No group of size 2 exists during any step.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == arr.length</code></li>
	<li><code>1 &lt;= m &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= n</code></li>
	<li>All integers in <code>arr</code> are <strong>distinct</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findLatestStep(self, arr: List[int], m: int) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa == pb:
                return
            p[pa] = pb
            size[pb] += size[pa]

        n = len(arr)
        if m == n:
            return n
        vis = [False] * n
        p = list(range(n))
        size = [1] * n
        ans = -1
        for i, v in enumerate(arr):
            v -= 1
            if v and vis[v - 1]:
                if size[find(v - 1)] == m:
                    ans = i
                union(v, v - 1)
            if v < n - 1 and vis[v + 1]:
                if size[find(v + 1)] == m:
                    ans = i
                union(v, v + 1)
            vis[v] = True
        return ans
```

```python
class Solution:
    def findLatestStep(self, arr: List[int], m: int) -> int:
        n = len(arr)
        if m == n:
            return n
        cnt = [0] * (n + 2)
        ans = -1
        for i, v in enumerate(arr):
            v -= 1
            l, r = cnt[v - 1], cnt[v + 1]
            if l == m or r == m:
                ans = i
            cnt[v - l] = cnt[v + r] = l + r + 1
        return ans
```

### **Java**

```java
class Solution {
    private int[] p;
    private int[] size;

    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        if (m == n) {
            return n;
        }
        boolean[] vis = new boolean[n];
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            int v = arr[i] - 1;
            if (v > 0 && vis[v - 1]) {
                if (size[find(v - 1)] == m) {
                    ans = i;
                }
                union(v, v - 1);
            }
            if (v < n - 1 && vis[v + 1]) {
                if (size[find(v + 1)] == m) {
                    ans = i;
                }
                union(v, v + 1);
            }
            vis[v] = true;
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return;
        }
        p[pa] = pb;
        size[pb] += size[pa];
    }
}
```

```java
class Solution {
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        if (m == n) {
            return n;
        }
        int[] cnt = new int[n + 2];
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            int v = arr[i];
            int l = cnt[v - 1], r = cnt[v + 1];
            if (l == m || r == m) {
                ans = i;
            }
            cnt[v - l] = l + r + 1;
            cnt[v + r] = l + r + 1;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;
    vector<int> size;

    int findLatestStep(vector<int>& arr, int m) {
        int n = arr.size();
        if (m == n) return n;
        p.resize(n);
        size.assign(n, 1);
        for (int i = 0; i < n; ++i) p[i] = i;
        int ans = -1;
        vector<int> vis(n);
        for (int i = 0; i < n; ++i) {
            int v = arr[i] - 1;
            if (v && vis[v - 1]) {
                if (size[find(v - 1)] == m) ans = i;
                unite(v, v - 1);
            }
            if (v < n - 1 && vis[v + 1]) {
                if (size[find(v + 1)] == m) ans = i;
                unite(v, v + 1);
            }
            vis[v] = true;
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return;
        p[pa] = pb;
        size[pb] += size[pa];
    }
};
```

```cpp
class Solution {
public:
    int findLatestStep(vector<int>& arr, int m) {
        int n = arr.size();
        if (m == n) return n;
        vector<int> cnt(n + 2);
        int ans = -1;
        for (int i = 0; i < n; ++i)
        {
            int v = arr[i];
            int l = cnt[v - 1], r = cnt[v + 1];
            if (l == m || r == m) ans = i;
            cnt[v - l] = cnt[v + r] = l + r + 1;
        }
        return ans;
    }
};
```

### **Go**

```go
func findLatestStep(arr []int, m int) int {
	n := len(arr)
	if m == n {
		return n
	}
	p := make([]int, n)
	size := make([]int, n)
	vis := make([]bool, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	union := func(a, b int) {
		pa, pb := find(a), find(b)
		if pa == pb {
			return
		}
		p[pa] = pb
		size[pb] += size[pa]
	}

	ans := -1
	for i, v := range arr {
		v--
		if v > 0 && vis[v-1] {
			if size[find(v-1)] == m {
				ans = i
			}
			union(v, v-1)
		}
		if v < n-1 && vis[v+1] {
			if size[find(v+1)] == m {
				ans = i
			}
			union(v, v+1)
		}
		vis[v] = true
	}
	return ans
}
```

```go
func findLatestStep(arr []int, m int) int {
	n := len(arr)
	if m == n {
		return n
	}
	cnt := make([]int, n+2)
	ans := -1
	for i, v := range arr {
		l, r := cnt[v-1], cnt[v+1]
		if l == m || r == m {
			ans = i
		}
		cnt[v-l], cnt[v+r] = l+r+1, l+r+1
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
