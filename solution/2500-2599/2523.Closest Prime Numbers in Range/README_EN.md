# [2523. Closest Prime Numbers in Range](https://leetcode.com/problems/closest-prime-numbers-in-range)

[中文文档](/solution/2500-2599/2523.Closest%20Prime%20Numbers%20in%20Range/README.md)

## Description

<p>Given two positive integers <code>left</code> and <code>right</code>, find the two integers <code>num1</code> and <code>num2</code> such that:</p>

<ul>
	<li><code>left &lt;= nums1 &lt; nums2 &lt;= right </code>.</li>
	<li><code>nums1</code> and <code>nums2</code> are both <strong>prime</strong> numbers.</li>
	<li><code>nums2 - nums1</code> is the <strong>minimum</strong> amongst all other pairs satisfying the above conditions.</li>
</ul>

<p>Return <em>the positive integer array</em> <code>ans = [nums1, nums2]</code>. <em>If there are multiple pairs satisfying these conditions, return the one with the minimum</em> <code>nums1</code> <em>value or</em> <code>[-1, -1]</code> <em>if such numbers do not exist.</em></p>

<p>A number greater than <code>1</code> is called <b>prime</b> if it is only divisible by <code>1</code> and itself.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> left = 10, right = 19
<strong>Output:</strong> [11,13]
<strong>Explanation:</strong> The prime numbers between 10 and 19 are 11, 13, 17, and 19.
The closest gap between any pair is 2, which can be achieved by [11,13] or [17,19].
Since 11 is smaller than 17, we return the first pair.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> left = 4, right = 6
<strong>Output:</strong> [-1,-1]
<strong>Explanation:</strong> There exists only one prime number in the given range, so the conditions cannot be satisfied.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= left &lt;= right &lt;= 10<sup>6</sup></code></li>
</ul>

<p>&nbsp;</p>
<style type="text/css">.spoilerbutton {display:block; border:dashed; padding: 0px 0px; margin:10px 0px; font-size:150%; font-weight: bold; color:#000000; background-color:cyan; outline:0; 
}
.spoiler {overflow:hidden;}
.spoiler > div {-webkit-transition: all 0s ease;-moz-transition: margin 0s ease;-o-transition: all 0s ease;transition: margin 0s ease;}
.spoilerbutton[value="Show Message"] + .spoiler > div {margin-top:-500%;}
.spoilerbutton[value="Hide Message"] + .spoiler {padding:5px;}
</style>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def closestPrimes(self, left: int, right: int) -> List[int]:
        cnt = 0
        st = [False] * (right + 1)
        prime = [0] * (right + 1)
        for i in range(2, right + 1):
            if not st[i]:
                prime[cnt] = i
                cnt += 1
            j = 0
            while prime[j] <= right // i:
                st[prime[j] * i] = 1
                if i % prime[j] == 0:
                    break
                j += 1
        p = [v for v in prime[:cnt] if left <= v <= right]
        mi = inf
        ans = [-1, -1]
        for a, b in pairwise(p):
            if (d := b - a) < mi:
                mi = d
                ans = [a, b]
        return ans
```

### **Java**

```java
class Solution {
    public int[] closestPrimes(int left, int right) {
        int cnt = 0;
        boolean[] st = new boolean[right + 1];
        int[] prime = new int[right + 1];
        for (int i = 2; i <= right; ++i) {
            if (!st[i]) {
                prime[cnt++] = i;
            }
            for (int j = 0; prime[j] <= right / i; ++j) {
                st[prime[j] * i] = true;
                if (i % prime[j] == 0) {
                    break;
                }
            }
        }
        int i = -1, j = -1;
        for (int k = 0; k < cnt; ++k) {
            if (prime[k] >= left && prime[k] <= right) {
                if (i == -1) {
                    i = k;
                }
                j = k;
            }
        }
        int[] ans = new int[] {-1, -1};
        if (i == j || i == -1) {
            return ans;
        }
        int mi = 1 << 30;
        for (int k = i; k < j; ++k) {
            int d = prime[k + 1] - prime[k];
            if (d < mi) {
                mi = d;
                ans[0] = prime[k];
                ans[1] = prime[k + 1];
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> closestPrimes(int left, int right) {
        int cnt = 0;
        bool st[right + 1];
        memset(st, 0, sizeof st);
        int prime[right + 1];
        for (int i = 2; i <= right; ++i) {
            if (!st[i]) {
                prime[cnt++] = i;
            }
            for (int j = 0; prime[j] <= right / i; ++j) {
                st[prime[j] * i] = true;
                if (i % prime[j] == 0) {
                    break;
                }
            }
        }
        int i = -1, j = -1;
        for (int k = 0; k < cnt; ++k) {
            if (prime[k] >= left && prime[k] <= right) {
                if (i == -1) {
                    i = k;
                }
                j = k;
            }
        }
        vector<int> ans = {-1, -1};
        if (i == j || i == -1) return ans;
        int mi = 1 << 30;
        for (int k = i; k < j; ++k) {
            int d = prime[k + 1] - prime[k];
            if (d < mi) {
                mi = d;
                ans[0] = prime[k];
                ans[1] = prime[k + 1];
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func closestPrimes(left int, right int) []int {
	cnt := 0
	st := make([]bool, right+1)
	prime := make([]int, right+1)
	for i := 2; i <= right; i++ {
		if !st[i] {
			prime[cnt] = i
			cnt++
		}
		for j := 0; prime[j] <= right/i; j++ {
			st[prime[j]*i] = true
			if i%prime[j] == 0 {
				break
			}
		}
	}
	i, j := -1, -1
	for k := 0; k < cnt; k++ {
		if prime[k] >= left && prime[k] <= right {
			if i == -1 {
				i = k
			}
			j = k
		}
	}
	ans := []int{-1, -1}
	if i == j || i == -1 {
		return ans
	}
	mi := 1 << 30
	for k := i; k < j; k++ {
		d := prime[k+1] - prime[k]
		if d < mi {
			mi = d
			ans[0], ans[1] = prime[k], prime[k+1]
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
