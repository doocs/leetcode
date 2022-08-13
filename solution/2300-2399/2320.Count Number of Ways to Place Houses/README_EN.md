# [2320. Count Number of Ways to Place Houses](https://leetcode.com/problems/count-number-of-ways-to-place-houses)

[中文文档](/solution/2300-2399/2320.Count%20Number%20of%20Ways%20to%20Place%20Houses/README.md)

## Description

<p>There is a street with <code>n * 2</code> <strong>plots</strong>, where there are <code>n</code> plots on each side of the street. The plots on each side are numbered from <code>1</code> to <code>n</code>. On each plot, a house can be placed.</p>

<p>Return <em>the number of ways houses can be placed such that no two houses are adjacent to each other on the same side of the street</em>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>Note that if a house is placed on the <code>i<sup>th</sup></code> plot on one side of the street, a house can also be placed on the <code>i<sup>th</sup></code> plot on the other side of the street.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 4
<strong>Explanation:</strong> 
Possible arrangements:
1. All plots are empty.
2. A house is placed on one side of the street.
3. A house is placed on the other side of the street.
4. Two houses are placed, one on each side of the street.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2320.Count%20Number%20of%20Ways%20to%20Place%20Houses/images/arrangements.png" style="width: 500px; height: 500px;" />
<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 9
<strong>Explanation:</strong> The 9 possible arrangements are shown in the diagram above.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countHousePlacements(self, n: int) -> int:
        mod = 10**9 + 7
        f = [[0] * 2 for _ in range(n)]
        f[0] = [1, 1]
        for i in range(1, n):
            f[i][0] = f[i - 1][0] + f[i - 1][1]
            f[i][1] = f[i - 1][0]
        s = sum(f[-1])
        return (s * s) % mod
```

### **Java**

```java
class Solution {
    public int countHousePlacements(int n) {
        int mod = (int) 1e9 + 7;
        long[][] f = new long[n][2];
        f[0] = new long[]{1, 1};
        for (int i = 1; i < n; ++i) {
            f[i][0] = (f[i - 1][0] + f[i - 1][1]) % mod;
            f[i][1] = f[i - 1][0];
        }
        long s = f[n - 1][0] + f[n - 1][1];
        return (int) ((s * s) % mod);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countHousePlacements(int n) {
        int mod = 1e9 + 7;
        vector<vector<long>> f(n, vector<long>(2));
        f[0] = {1, 1};
        for (int i = 1; i < n; ++i) {
            f[i][0] = (f[i - 1][0] + f[i - 1][1]) % mod;
            f[i][1] = f[i - 1][0];
        }
        long s = f[n - 1][0] + f[n - 1][1];
        return (int)((s * s) % mod);
    }
};
```

### **Go**

```go
func countHousePlacements(n int) int {
	mod := int(1e9) + 7
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, 2)
	}
	f[0] = []int{1, 1}
	for i := 1; i < n; i++ {
		f[i][0] = (f[i-1][0] + f[i-1][1]) % mod
		f[i][1] = f[i-1][0]
	}
	s := f[n-1][0] + f[n-1][1]
	return (s * s) % mod
}
```

### **TypeScript**

```ts
function countHousePlacements(n: number): number {
    const mod = BigInt(10 ** 9 + 7);
    let pre = 1n,
        count = 2n;
    for (let i = 2; i <= n; i++) {
        [count, pre] = [(count + pre) % mod, count];
    }
    return Number(count ** 2n % mod);
}
```

### **...**

```

```

<!-- tabs:end -->
