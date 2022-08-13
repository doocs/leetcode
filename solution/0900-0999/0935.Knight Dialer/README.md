# [935. 骑士拨号器](https://leetcode.cn/problems/knight-dialer)

[English Version](/solution/0900-0999/0935.Knight%20Dialer/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>象棋骑士有一个<strong>独特的移动方式</strong>，它可以垂直移动两个方格，水平移动一个方格，或者水平移动两个方格，垂直移动一个方格(两者都形成一个&nbsp;<strong>L&nbsp;</strong>的形状)。</p>

<p>象棋骑士可能的移动方式如下图所示:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0935.Knight%20Dialer/images/chess.jpg" style="height: 200px; width: 200px;" /></p>

<p>我们有一个象棋骑士和一个电话垫，如下所示，骑士<strong>只能站在一个数字单元格上</strong>(即蓝色单元格)。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0935.Knight%20Dialer/images/phone.jpg" style="height: 200px; width: 150px;" /></p>

<p>给定一个整数 n，返回我们可以拨多少个长度为 n 的不同电话号码。</p>

<p>你可以将骑士放置在<strong>任何数字单元格</strong>上，然后你应该执行 n - 1 次移动来获得长度为 n 的号码。所有的跳跃应该是<strong>有效</strong>的骑士跳跃。</p>

<p>因为答案可能很大，<strong>所以输出答案模&nbsp;</strong><code>10<sup>9</sup>&nbsp;+ 7</code>.</p>

<p>&nbsp;</p>

<ul>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>10
<strong>解释：</strong>我们需要拨一个长度为1的数字，所以把骑士放在10个单元格中的任何一个数字单元格上都能满足条件。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>20
<strong>解释：</strong>我们可以拨打的所有有效号码为[04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 3131
<strong>输出：</strong>136006598
<strong>解释：</strong>注意取模
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递推**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
