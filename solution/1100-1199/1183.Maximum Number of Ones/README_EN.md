# [1183. Maximum Number of Ones](https://leetcode.com/problems/maximum-number-of-ones)

[中文文档](/solution/1100-1199/1183.Maximum%20Number%20of%20Ones/README.md)

## Description

<p>Consider a matrix <code>M</code> with dimensions <code>width * height</code>, such that every cell has value <code>0</code>&nbsp;or <code>1</code>, and any <strong>square</strong>&nbsp;sub-matrix of <code>M</code> of size <code>sideLength * sideLength</code>&nbsp;has at most <code>maxOnes</code>&nbsp;ones.</p>

<p>Return the maximum possible number of ones that the matrix <code>M</code> can have.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> width = 3, height = 3, sideLength = 2, maxOnes = 1
<strong>Output:</strong> 4
<strong>Explanation:</strong>
In a 3*3 matrix, no 2*2 sub-matrix can have more than 1 one.
The best solution that has 4 ones is:
[1,0,1]
[0,0,0]
[1,0,1]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> width = 3, height = 3, sideLength = 2, maxOnes = 2
<strong>Output:</strong> 6
<strong>Explanation:</strong>
[1,0,1]
[1,0,1]
[1,0,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= width, height &lt;= 100</code></li>
	<li><code>1 &lt;= sideLength &lt;= width, height</code></li>
	<li><code>0 &lt;= maxOnes &lt;= sideLength * sideLength</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumNumberOfOnes(
        self, width: int, height: int, sideLength: int, maxOnes: int
    ) -> int:
        x = sideLength
        cnt = [0] * (x * x)
        for i in range(width):
            for j in range(height):
                k = (i % x) * x + (j % x)
                cnt[k] += 1
        cnt.sort(reverse=True)
        return sum(cnt[:maxOnes])
```

### **Java**

```java
class Solution {
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int x = sideLength;
        int[] cnt = new int[x * x];
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int k = (i % x) * x + (j % x);
                ++cnt[k];
            }
        }
        Arrays.sort(cnt);
        int ans = 0;
        for (int i = 0; i < maxOnes; ++i) {
            ans += cnt[cnt.length - i - 1];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int x = sideLength;
        vector<int> cnt(x * x);
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int k = (i % x) * x + (j % x);
                ++cnt[k];
            }
        }
        sort(cnt.rbegin(), cnt.rend());
        int ans = 0;
        for (int i = 0; i < maxOnes; ++i) {
            ans += cnt[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumNumberOfOnes(width int, height int, sideLength int, maxOnes int) int {
	x := sideLength
	cnt := make([]int, x*x)
	for i := 0; i < width; i++ {
		for j := 0; j < height; j++ {
			k := (i%x)*x + (j % x)
			cnt[k]++
		}
	}
	sort.Ints(cnt)
	ans := 0
	for i := range cnt[:maxOnes] {
		ans += cnt[len(cnt)-i-1]
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number} width
 * @param {number} height
 * @param {number} sideLength
 * @param {number} maxOnes
 * @return {number}
 */
var maximumNumberOfOnes = function (width, height, sideLength, maxOnes) {
    const x = sideLength;
    const cnt = new Array(x * x).fill(0);
    for (let i = 0; i < width; ++i) {
        for (let j = 0; j < height; ++j) {
            const k = (i % x) * x + (j % x);
            ++cnt[k];
        }
    }
    cnt.sort((a, b) => b - a);
    let ans = 0;
    for (let i = 0; i < maxOnes; ++i) {
        ans += cnt[i];
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
