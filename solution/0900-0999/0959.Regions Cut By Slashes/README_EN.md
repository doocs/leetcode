# [959. Regions Cut By Slashes](https://leetcode.com/problems/regions-cut-by-slashes)

[中文文档](/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/README.md)

## Description

<p>In a N x N&nbsp;<code>grid</code> composed of 1 x 1 squares, each 1 x 1 square consists of a <code>/</code>, <code>\</code>, or blank space.&nbsp; These characters divide the square into contiguous regions.</p>

<p>(Note that backslash characters are escaped, so a <code>\</code>&nbsp;is represented as <code>&quot;\\&quot;</code>.)</p>

<p>Return the number of regions.</p>

<p>&nbsp;</p>

<div>

<div>

<div>

<div>

<div>

<ol>

</ol>

</div>

</div>

</div>

</div>

</div>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:

</strong><span id="example-input-1-1">[

&nbsp; &quot; /&quot;,

&nbsp; &quot;/ &quot;

]</span>

<strong>Output: </strong><span id="example-output-1">2</span>

<strong>Explanation: </strong>The 2x2 grid is as follows:

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/images/1.png" style="width: 82px; height: 82px;" />

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:

</strong><span id="example-input-2-1">[

&nbsp; &quot; /&quot;,

&nbsp; &quot;  &quot;

]</span>

<strong>Output: </strong><span id="example-output-2">1</span>

<strong>Explanation: </strong>The 2x2 grid is as follows:

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/images/2.png" style="width: 82px; height: 82px;" />

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:

</strong><span id="example-input-3-1">[

&nbsp; &quot;\\/&quot;,

&nbsp; &quot;/\\&quot;

]</span>

<strong>Output: </strong><span id="example-output-3">4</span>

<strong>Explanation: </strong>(Recall that because \ characters are escaped, &quot;\\/&quot; refers to \/, and &quot;/\\&quot; refers to /\.)

The 2x2 grid is as follows:

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/images/3.png" style="width: 82px; height: 82px;" />

</pre>

<div>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:

</strong><span id="example-input-4-1">[

&nbsp; &quot;/\\&quot;,

&nbsp; &quot;\\/&quot;

]</span>

<strong>Output: </strong><span id="example-output-4">5</span>

<strong>Explanation: </strong>(Recall that because \ characters are escaped, &quot;/\\&quot; refers to /\, and &quot;\\/&quot; refers to \/.)

The 2x2 grid is as follows:

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/images/4.png" style="width: 82px; height: 82px;" />

</pre>

<div>

<p><strong>Example 5:</strong></p>

<pre>

<strong>Input:

</strong><span id="example-input-5-1">[

&nbsp; &quot;//&quot;,

&nbsp; &quot;/ &quot;

]</span>

<strong>Output: </strong><span id="example-output-5">3</span>

<strong>Explanation: </strong>The 2x2 grid is as follows:

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/images/5.png" style="width: 82px; height: 82px;" />

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= grid.length == grid[0].length &lt;= 30</code></li>
	<li><code>grid[i][j]</code> is either <code>&#39;/&#39;</code>, <code>&#39;\&#39;</code>, or <code>&#39; &#39;</code>.</li>
</ol>

</div>

</div>

</div>

</div>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def regionsBySlashes(self, grid: List[str]) -> int:
        n = len(grid)
        p = list(range(n * n * 4))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(n):
            for j in range(n):
                idx = i * n + j
                if i < n - 1:
                    p[find(idx * 4 + 2)] = find((idx + n) * 4)
                if j < n - 1:
                    p[find(idx * 4 + 1)] = find((idx + 1) * 4 + 3)

                if grid[i][j] == '/':
                    p[find(idx * 4)] = find(idx * 4 + 3)
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2)
                elif grid[i][j] == '\\':
                    p[find(idx * 4)] = find(idx * 4 + 1)
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3)
                else:
                    p[find(idx * 4)] = find(idx * 4 + 1)
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2)
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3)
        s = set()
        for i in range(len(p)):
            s.add(find(i))
        return len(s)
```

### **Java**

```java
class Solution {
    private int[] p;

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        p = new int[n * n * 4];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < n; ++i) {
            char[] row = grid[i].toCharArray();
            for (int j = 0; j < n; ++j) {
                int idx = i * n + j;
                if (i < n - 1) {
                    p[find(idx * 4 + 2)] = find((idx + n) * 4);
                }
                if (j < n - 1) {
                    p[find(idx * 4 + 1)] = find((idx + 1) * 4 + 3);
                }

                if (row[j] == '/') {
                    p[find(idx * 4)] = find(idx * 4 + 3);
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2);
                } else if (row[j] == '\\') {
                    p[find(idx * 4)] = find(idx * 4 + 1);
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3);
                } else {
                    p[find(idx * 4)] = find(idx * 4 + 1);
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2);
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3);
                }
            }
        }
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < p.length; ++i) {
            s.add(find(i));
        }
        return s.size();
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

    int regionsBySlashes(vector<string>& grid) {
        int n = grid.size();
        for (int i = 0; i < n * n * 4; ++i) p.push_back(i);
        for (int i = 0; i < n; ++i) {
            string row = grid[i];
            for (int j = 0; j < n; ++j) {
                int idx = i * n + j;
                if (i < n - 1) p[find(idx * 4 + 2)] = find((idx + n) * 4);
                if (j < n - 1) p[find(idx * 4 + 1)] = find((idx + 1) * 4 + 3);
                if (row[j] == '/')
                {
                    p[find(idx * 4)] = find(idx * 4 + 3);
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2);
                }
                else if (row[j] == '\\')
                {
                    p[find(idx * 4)] = find(idx * 4 + 1);
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3);
                }
                else
                {
                    p[find(idx * 4)] = find(idx * 4 + 1);
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2);
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3);
                }
            }
        }
        unordered_set<int> s;
        for (int i = 0; i < p.size(); ++i)
            s.insert(find(i));
        return s.size();
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

func regionsBySlashes(grid []string) int {
	n := len(grid)
	p = make([]int, n*n*4)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	for i := 0; i < n; i++ {
		row := grid[i]
		for j := 0; j < n; j++ {
			idx := i*n + j
			if i < n-1 {
				p[find(idx*4+2)] = find((idx + n) * 4)
			}
			if j < n-1 {
				p[find(idx*4+1)] = find((idx+1)*4 + 3)
			}
			if row[j] == '/' {
				p[find(idx*4)] = find(idx*4 + 3)
				p[find(idx*4+1)] = find(idx*4 + 2)
			} else if row[j] == '\\' {
				p[find(idx*4)] = find(idx*4 + 1)
				p[find(idx*4+2)] = find(idx*4 + 3)
			} else {
				p[find(idx*4)] = find(idx*4 + 1)
				p[find(idx*4+1)] = find(idx*4 + 2)
				p[find(idx*4+2)] = find(idx*4 + 3)
			}
		}
	}
	s := make(map[int]bool)
	for i := 0; i < len(p); i++ {
		s[find(i)] = true
	}
	return len(s)
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
