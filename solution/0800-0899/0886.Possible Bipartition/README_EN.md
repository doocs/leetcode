# [886. Possible Bipartition](https://leetcode.com/problems/possible-bipartition)

[中文文档](/solution/0800-0899/0886.Possible%20Bipartition/README.md)

## Description

<p>Given a set of <code>N</code>&nbsp;people (numbered <code>1, 2, ..., N</code>), we would like to split everyone into two groups of <strong>any</strong> size.</p>

<p>Each person may dislike some other people, and they should not go into the same group.&nbsp;</p>

<p>Formally, if <code>dislikes[i] = [a, b]</code>, it means it is not allowed to put the people numbered <code>a</code> and <code>b</code> into the same group.</p>

<p>Return <code>true</code>&nbsp;if and only if it is possible to split everyone into two groups in this way.</p>

<p>&nbsp;</p>

<div>

<div>

<ol>

</ol>

</div>

</div>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>N = <span id="example-input-1-1">4</span>, dislikes = <span id="example-input-1-2">[[1,2],[1,3],[2,4]]</span>

<strong>Output: </strong><span id="example-output-1">true</span>

<strong>Explanation</strong>: group1 [1,4], group2 [2,3]

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>N = <span id="example-input-2-1">3</span>, dislikes = <span id="example-input-2-2">[[1,2],[1,3],[2,3]]</span>

<strong>Output: </strong><span id="example-output-2">false</span>

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong>N = <span id="example-input-3-1">5</span>, dislikes = <span id="example-input-3-2">[[1,2],[2,3],[3,4],[4,5],[1,5]]</span>

<strong>Output: </strong><span id="example-output-3">false</span>

</pre>

</div>

</div>

</div>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= N &lt;= 2000</code></li>
	<li><code>0 &lt;= dislikes.length &lt;= 10000</code></li>
	<li><code>dislikes[i].length == 2</code></li>
	<li><code>1 &lt;= dislikes[i][j] &lt;= N</code></li>
	<li><code>dislikes[i][0] &lt; dislikes[i][1]</code></li>
	<li>There does not exist <code>i != j</code> for which <code>dislikes[i] == dislikes[j]</code>.</li>
</ul>

## Solutions

Union find.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def possibleBipartition(self, n: int, dislikes: List[List[int]]) -> bool:
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        mp = defaultdict(list)
        for i, j in dislikes:
            mp[i - 1].append(j - 1)
            mp[j - 1].append(i - 1)
        for i in range(n):
            dis = mp[i]
            for j in dis:
                if find(i) == find(j):
                    return False
                p[find(j)] = find(dis[0])
        return True
```

### **Java**

```java
class Solution {
public:
    vector<int> p;

    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        unordered_map<int, vector<int>> mp;
        for (auto e : dislikes)
        {
            mp[e[0] - 1].push_back(e[1] - 1);
            mp[e[1] - 1].push_back(e[0] - 1);
        }
        for (int i = 0; i < n; ++i)
        {
            auto dis = mp[i];
            for (int j : dis)
            {
                if (find(i) == find(j)) return false;
                p[find(j)] = find(dis[0]);
            }
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        unordered_map<int, vector<int>> mp;
        for (auto e : dislikes)
        {
            mp[e[0] - 1].push_back(e[1] - 1);
            mp[e[1] - 1].push_back(e[0] - 1);
        }
        for (int i = 0; i < n; ++i)
        {
            auto dis = mp[i];
            for (int j : dis)
            {
                if (find(i) == find(j)) return false;
                p[find(j)] = find(dis[0]);
            }
        }
        return true;
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

func possibleBipartition(n int, dislikes [][]int) bool {
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	mp := make(map[int][]int)
	for _, e := range dislikes {
		mp[e[0]-1] = append(mp[e[0]-1], e[1]-1)
		mp[e[1]-1] = append(mp[e[1]-1], e[0]-1)
	}
	for i := 0; i < n; i++ {
		dis := mp[i]
		for _, j := range dis {
			if find(i) == find(j) {
				return false
			}
			p[find(j)] = find(dis[0])
		}
	}
	return true
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
