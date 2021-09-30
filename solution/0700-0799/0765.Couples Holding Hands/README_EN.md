# [765. Couples Holding Hands](https://leetcode.com/problems/couples-holding-hands)

[中文文档](/solution/0700-0799/0765.Couples%20Holding%20Hands/README.md)

## Description

<p>

N couples sit in 2N seats arranged in a row and want to hold hands. We want to know the minimum number of swaps so that every couple is sitting side by side. A <i>swap</i> consists of choosing <b>any</b> two people, then they stand up and switch seats.

</p><p>

The people and seats are represented by an integer from <code>0</code> to <code>2N-1</code>, the couples are numbered in order, the first couple being <code>(0, 1)</code>, the second couple being <code>(2, 3)</code>, and so on with the last couple being <code>(2N-2, 2N-1)</code>.

</p><p>

The couples' initial seating is given by <code>row[i]</code> being the value of the person who is initially sitting in the i-th seat.

<p><b>Example 1:</b><br /><pre>

<b>Input:</b> row = [0, 2, 1, 3]

<b>Output:</b> 1

<b>Explanation:</b> We only need to swap the second (row[1]) and third (row[2]) person.

</pre></p>

<p><b>Example 2:</b><br /><pre>

<b>Input:</b> row = [3, 2, 0, 1]

<b>Output:</b> 0

<b>Explanation:</b> All couples are already seated side by side.

</pre></p>

<p>

<b>Note:</b>

<ol>

<li> <code>len(row)</code> is even and in the range of <code>[4, 60]</code>.</li>

<li> <code>row</code> is guaranteed to be a permutation of <code>0...len(row)-1</code>.</li>

</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minSwapsCouples(self, row: List[int]) -> int:
        n = len(row) >> 1
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(0, len(row), 2):
            a, b = row[i] >> 1, row[i + 1] >> 1
            p[find(a)] = find(b)

        cnt = 0
        for i in range(n):
            if i == find(i):
                cnt += 1
        return n - cnt
```

### **Java**

```java
class Solution {
    private int[] p;

    public int minSwapsCouples(int[] row) {
        int n = row.length >> 1;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < row.length; i += 2) {
            int a = row[i] >> 1, b = row[i + 1] >> 1;
            p[find(a)] = find(b);
        }
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (i == find(i)) {
                ++cnt;
            }
        }
        return n - cnt;
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

    int minSwapsCouples(vector<int> &row) {
        int n = row.size() >> 1;
        p.resize(n);
        for (int i = 0; i < n; ++i)
        {
            p[i] = i;
        }
        for (int i = 0; i < row.size(); i += 2)
        {
            int a = row[i] >> 1, b = row[i + 1] >> 1;
            p[find(a)] = find(b);
        }
        int cnt = 0;
        for (int i = 0; i < n; ++i)
        {
            if (i == find(i))
                ++cnt;
        }
        return n - cnt;
    }

    int find(int x) {
        if (p[x] != x)
        {
            p[x] = find(p[x]);
        }
        return p[x];
    }
};
```

### **Go**

```go
var p []int

func minSwapsCouples(row []int) int {
	n := len(row) >> 1
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	for i := 0; i < len(row); i += 2 {
		a, b := row[i]>>1, row[i+1]>>1
		p[find(a)] = find(b)
	}
	cnt := 0
	for i := 0; i < n; i++ {
		if i == find(i) {
			cnt++
		}
	}
	return n - cnt
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
