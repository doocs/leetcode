# [1238. Circular Permutation in Binary Representation](https://leetcode.com/problems/circular-permutation-in-binary-representation)

[中文文档](/solution/1200-1299/1238.Circular%20Permutation%20in%20Binary%20Representation/README.md)

## Description

<p>Given 2 integers <code>n</code> and <code>start</code>. Your task is return <strong>any</strong> permutation <code>p</code>&nbsp;of <code>(0,1,2.....,2^n -1) </code>such that :</p>

<ul>
    <li><code>p[0] = start</code></li>
    <li><code>p[i]</code> and <code>p[i+1]</code>&nbsp;differ by only one bit in their binary representation.</li>
    <li><code>p[0]</code> and <code>p[2^n -1]</code>&nbsp;must also differ by only one bit in their binary representation.</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> n = 2, start = 3

<strong>Output:</strong> [3,2,0,1]

<strong>Explanation:</strong> The binary representation of the permutation is (11,10,00,01). 

All the adjacent element differ by one bit. Another valid permutation is [3,1,0,2]

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> n = 3, start = 2

<strong>Output:</strong> [2,6,7,5,4,0,1,3]

<strong>Explanation:</strong> The binary representation of the permutation is (010,110,111,101,100,000,001,011).

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>1 &lt;= n &lt;= 16</code></li>
    <li><code>0 &lt;= start&nbsp;&lt;&nbsp;2 ^ n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def circularPermutation(self, n: int, start: int) -> List[int]:
        g = [i ^ (i >> 1) for i in range(1 << n)]
        j = g.index(start)
        return g[j:] + g[:j]
```

```python
class Solution:
    def circularPermutation(self, n: int, start: int) -> List[int]:
        return [i ^ (i >> 1) ^ start for i in range(1 << n)]
```

### **Java**

```java
class Solution {
    public List<Integer> circularPermutation(int n, int start) {
        int[] g = new int[1 << n];
        int j = 0;
        for (int i = 0; i < 1 << n; ++i) {
            g[i] = i ^ (i >> 1);
            if (g[i] == start) {
                j = i;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = j; i < j + (1 << n); ++i) {
            ans.add(g[i % (1 << n)]);
        }
        return ans;
    }
}
```

```java
class Solution {
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i) {
            ans.add(i ^ (i >> 1) ^ start);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> circularPermutation(int n, int start) {
        int g[1 << n];
        int j = 0;
        for (int i = 0; i < 1 << n; ++i) {
            g[i] = i ^ (i >> 1);
            if (g[i] == start) {
                j = i;
            }
        }
        vector<int> ans;
        for (int i = j; i < j + (1 << n); ++i) {
            ans.push_back(g[i % (1 << n)]);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> circularPermutation(int n, int start) {
        vector<int> ans(1 << n);
        for (int i = 0; i < 1 << n; ++i) {
            ans[i] = i ^ (i >> 1) ^ start;
        }
        return ans;
    }
};
```

### **Go**

```go
func circularPermutation(n int, start int) []int {
	g := make([]int, 1<<n)
	j := 0
	for i := range g {
		g[i] = i ^ (i >> 1)
		if g[i] == start {
			j = i
		}
	}
	return append(g[j:], g[:j]...)
}
```

```go
func circularPermutation(n int, start int) (ans []int) {
	for i := 0; i < 1<<n; i++ {
		ans = append(ans, i^(i>>1)^start)
	}
	return
}
```

### **TypeScript**

```ts
function circularPermutation(n: number, start: number): number[] {
    const ans: number[] = [];
    for (let i = 0; i < 1 << n; ++i) {
        ans.push(i ^ (i >> 1) ^ start);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
