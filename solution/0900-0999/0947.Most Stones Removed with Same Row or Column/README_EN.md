# [947. Most Stones Removed with Same Row or Column](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column)

[中文文档](/solution/0900-0999/0947.Most%20Stones%20Removed%20with%20Same%20Row%20or%20Column/README.md)

## Description

<p>On a 2D plane, we place <code>n</code> stones at some integer coordinate points. Each coordinate point may have at most one stone.</p>

<p>A stone can be removed if it shares either <strong>the same row or the same column</strong> as another stone that has not been removed.</p>

<p>Given an array <code>stones</code> of length <code>n</code> where <code>stones[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents the location of the <code>i<sup>th</sup></code> stone, return <em>the largest possible number of stones that can be removed</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
<strong>Output:</strong> 5
<strong>Explanation:</strong> One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> One way to make 3 moves is as follows:
1. Remove stone [2,2] because it shares the same row as [2,0].
2. Remove stone [2,0] because it shares the same column as [0,0].
3. Remove stone [0,2] because it shares the same row as [0,0].
Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> stones = [[0,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> [0,0] is the only stone on the plane, so you cannot remove it.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= stones.length &lt;= 1000</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>No two stones are at the same coordinate point.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def removeStones(self, stones: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = 10010
        p = list(range(n << 1))
        for x, y in stones:
            p[find(x)] = find(y + n)

        s = {find(x) for x, _ in stones}
        return len(stones) - len(s)
```

### **Java**

```java
class Solution {
    private int[] p;

    public int removeStones(int[][] stones) {
        int n = 10010;
        p = new int[n << 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int[] stone : stones) {
            p[find(stone[0])] = find(stone[1] + n);
        }
        Set<Integer> s = new HashSet<>();
        for (int[] stone : stones) {
            s.add(find(stone[0]));
        }
        return stones.length - s.size();
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

    int removeStones(vector<vector<int>>& stones) {
        int n = 10010;
        p.resize(n << 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (auto& stone : stones) p[find(stone[0])] = find(stone[1] + n);
        unordered_set<int> s;
        for (auto& stone : stones) s.insert(find(stone[0]));
        return stones.size() - s.size();
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func removeStones(stones [][]int) int {
	n := 10010
	p := make([]int, n<<1)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, stone := range stones {
		p[find(stone[0])] = find(stone[1] + n)
	}
	s := make(map[int]bool)
	for _, stone := range stones {
		s[find(stone[0])] = true
	}
	return len(stones) - len(s)
}
```

### **...**

```

```

<!-- tabs:end -->
