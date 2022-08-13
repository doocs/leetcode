# [935. Knight Dialer](https://leetcode.com/problems/knight-dialer)

[中文文档](/solution/0900-0999/0935.Knight%20Dialer/README.md)

## Description

<p>The chess knight has a <strong>unique movement</strong>,&nbsp;it may move two squares vertically and one square horizontally, or two squares horizontally and one square vertically (with both forming the shape of an <strong>L</strong>). The possible movements of chess knight are shown in this diagaram:</p>

<p>A chess knight can move as indicated in the chess diagram below:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0935.Knight%20Dialer/images/chess.jpg" style="width: 402px; height: 402px;" />
<p>We have a chess knight and a phone pad as shown below, the knight <strong>can only stand on a numeric cell</strong>&nbsp;(i.e. blue cell).</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0935.Knight%20Dialer/images/phone.jpg" style="width: 242px; height: 322px;" />
<p>Given an integer <code>n</code>, return how many distinct phone numbers of length <code>n</code> we can dial.</p>

<p>You are allowed to place the knight <strong>on any numeric cell</strong> initially and then you should perform <code>n - 1</code> jumps to dial a number of length <code>n</code>. All jumps should be <strong>valid</strong> knight jumps.</p>

<p>As the answer may be very large, <strong>return the answer modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 10
<strong>Explanation:</strong> We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is sufficient.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 20
<strong>Explanation:</strong> All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3131
<strong>Output:</strong> 136006598
<strong>Explanation:</strong> Please take care of the mod.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def knightDialer(self, n: int) -> int:
        if n == 1:
            return 10
        f = [1] * 10
        for _ in range(n - 1):
            t = [0] * 10
            t[0] = f[4] + f[6]
            t[1] = f[6] + f[8]
            t[2] = f[7] + f[9]
            t[3] = f[4] + f[8]
            t[4] = f[0] + f[3] + f[9]
            t[6] = f[0] + f[1] + f[7]
            t[7] = f[2] + f[6]
            t[8] = f[1] + f[3]
            t[9] = f[2] + f[4]
            f = t
        return sum(t) % (10**9 + 7)
```

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int knightDialer(int n) {
        if (n == 1) {
            return 10;
        }
        long[] f = new long[10];
        Arrays.fill(f, 1);
        while (--n > 0) {
            long[] t = new long[10];
            t[0] = f[4] + f[6];
            t[1] = f[6] + f[8];
            t[2] = f[7] + f[9];
            t[3] = f[4] + f[8];
            t[4] = f[0] + f[3] + f[9];
            t[6] = f[0] + f[1] + f[7];
            t[7] = f[2] + f[6];
            t[8] = f[1] + f[3];
            t[9] = f[2] + f[4];
            for (int i = 0; i < 10; ++i) {
                f[i] = t[i] % MOD;
            }
        }
        long ans = 0;
        for (long v : f) {
            ans = (ans + v) % MOD;
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
using ll = long long;

class Solution {
public:
    int knightDialer(int n) {
        if (n == 1) return 10;
        int mod = 1e9 + 7;
        vector<ll> f(10, 1ll);
        while (--n) {
            vector<ll> t(10);
            t[0] = f[4] + f[6];
            t[1] = f[6] + f[8];
            t[2] = f[7] + f[9];
            t[3] = f[4] + f[8];
            t[4] = f[0] + f[3] + f[9];
            t[6] = f[0] + f[1] + f[7];
            t[7] = f[2] + f[6];
            t[8] = f[1] + f[3];
            t[9] = f[2] + f[4];
            for (int i = 0; i < 10; ++i) f[i] = t[i] % mod;
        }
        ll ans = accumulate(f.begin(), f.end(), 0ll);
        return (int)(ans % mod);
    }
};
```

### **Go**

```go
func knightDialer(n int) int {
	if n == 1 {
		return 10
	}
	f := make([]int, 10)
	for i := range f {
		f[i] = 1
	}
	mod := int(1e9) + 7
	for i := 1; i < n; i++ {
		t := make([]int, 10)
		t[0] = f[4] + f[6]
		t[1] = f[6] + f[8]
		t[2] = f[7] + f[9]
		t[3] = f[4] + f[8]
		t[4] = f[0] + f[3] + f[9]
		t[6] = f[0] + f[1] + f[7]
		t[7] = f[2] + f[6]
		t[8] = f[1] + f[3]
		t[9] = f[2] + f[4]
		for j, v := range t {
			f[j] = v % mod
		}
	}
	ans := 0
	for _, v := range f {
		ans = (ans + v) % mod
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
