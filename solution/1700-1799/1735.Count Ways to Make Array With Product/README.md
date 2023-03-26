# [1735. 生成乘积数组的方案数](https://leetcode.cn/problems/count-ways-to-make-array-with-product)

[English Version](/solution/1700-1799/1735.Count%20Ways%20to%20Make%20Array%20With%20Product/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组 <code>queries</code> ，其中 <code>queries[i] = [n<sub>i</sub>, k<sub>i</sub>]</code> 。第 <code>i</code> 个查询 <code>queries[i]</code> 要求构造长度为 <code>n<sub>i</sub></code> 、每个元素都是正整数的数组，且满足所有元素的乘积为 <code>k<sub>i</sub></code><sub> </sub>，请你找出有多少种可行的方案。由于答案可能会很大，方案数需要对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 。</p>

<p>请你返回一个整数数组<em> </em><code>answer</code>，满足<em> </em><code>answer.length == queries.length</code> ，其中<em> </em><code>answer[i]</code>是第<em> </em><code>i</code> 个查询的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>queries = [[2,6],[5,1],[73,660]]
<b>输出：</b>[4,1,50734910]
<b>解释：</b>每个查询之间彼此独立。
[2,6]：总共有 4 种方案得到长度为 2 且乘积为 6 的数组：[1,6]，[2,3]，[3,2]，[6,1]。
[5,1]：总共有 1 种方案得到长度为 5 且乘积为 1 的数组：[1,1,1,1,1]。
[73,660]：总共有 1050734917 种方案得到长度为 73 且乘积为 660 的数组。1050734917 对 10<sup>9</sup> + 7 取余得到 50734910 。
</pre>

<p><strong>示例 2 ：</strong></p>

<pre>
<b>输入：</b>queries = [[1,1],[2,2],[3,3],[4,4],[5,5]]
<b>输出：</b>[1,2,3,10,5]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= queries.length <= 10<sup>4</sup> </code></li>
	<li><code>1 <= n<sub>i</sub>, k<sub>i</sub> <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
N = 10020
MOD = 10**9 + 7
f = [1] * N
g = [1] * N
p = defaultdict(list)
for i in range(1, N):
    f[i] = f[i - 1] * i % MOD
    g[i] = pow(f[i], MOD - 2, MOD)
    x = i
    j = 2
    while j <= x // j:
        if x % j == 0:
            cnt = 0
            while x % j == 0:
                cnt += 1
                x //= j
            p[i].append(cnt)
        j += 1
    if x > 1:
        p[i].append(1)


def comb(n, k):
    return f[n] * g[k] * g[n - k] % MOD


class Solution:
    def waysToFillArray(self, queries: List[List[int]]) -> List[int]:
        ans = []
        for n, k in queries:
            t = 1
            for x in p[k]:
                t = t * comb(x + n - 1, n - 1) % MOD
            ans.append(t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int N = 10020;
    private static final int MOD = (int) 1e9 + 7;
    private static final long[] F = new long[N];
    private static final long[] G = new long[N];
    private static final List<Integer>[] P = new List[N];

    static {
        F[0] = 1;
        G[0] = 1;
        Arrays.setAll(P, k -> new ArrayList<>());
        for (int i = 1; i < N; ++i) {
            F[i] = F[i - 1] * i % MOD;
            G[i] = qmi(F[i], MOD - 2, MOD);
            int x = i;
            for (int j = 2; j <= x / j; ++j) {
                if (x % j == 0) {
                    int cnt = 0;
                    while (x % j == 0) {
                        ++cnt;
                        x /= j;
                    }
                    P[i].add(cnt);
                }
            }
            if (x > 1) {
                P[i].add(1);
            }
        }
    }

    public static long qmi(long a, long k, long p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }

    public static long comb(int n, int k) {
        return (F[n] * G[k] % MOD) * G[n - k] % MOD;
    }


    public int[] waysToFillArray(int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int n = queries[i][0], k = queries[i][1];
            long t = 1;
            for (int x : P[k]) {
                t = t * comb(x + n - 1, n - 1) % MOD;
            }
            ans[i] = (int) t;
        }
        return ans;
    }
}
```

### **C++**

```cpp
int N = 10020;
int MOD = 1e9 + 7;
long f[10020];
long g[10020];
vector<int> p[10020];

long qmi(long a, long k, long p) {
    long res = 1;
    while (k != 0) {
        if ((k & 1) == 1) {
            res = res * a % p;
        }
        k >>= 1;
        a = a * a % p;
    }
    return res;
}

int init = []() {
    f[0] = 1;
    g[0] = 1;
    for (int i = 1; i < N; ++i) {
        f[i] = f[i - 1] * i % MOD;
        g[i] = qmi(f[i], MOD - 2, MOD);
        int x = i;
        for (int j = 2; j <= x / j; ++j) {
            if (x % j == 0) {
                int cnt = 0;
                while (x % j == 0) {
                    ++cnt;
                    x /= j;
                }
                p[i].push_back(cnt);
            }
        }
        if (x > 1) {
            p[i].push_back(1);
        }
    }
    return 0;
}();

int comb(int n, int k) {
    return (f[n] * g[k] % MOD) * g[n - k] % MOD;
}

class Solution {
public:
    vector<int> waysToFillArray(vector<vector<int>>& queries) {
        vector<int> ans;
        for (auto& q : queries) {
            int n = q[0], k = q[1];
            long long t = 1;
            for (int x : p[k]) {
                t = t * comb(x + n - 1, n - 1) % MOD;
            }
            ans.push_back(t);
        }
        return ans;
    }
};
```

### **Go**

```go
const n = 1e4 + 20
const mod = 1e9 + 7

var f = make([]int, n)
var g = make([]int, n)
var p = make([][]int, n)

func qmi(a, k, p int) int {
	res := 1
	for k != 0 {
		if k&1 == 1 {
			res = res * a % p
		}
		k >>= 1
		a = a * a % p
	}
	return res
}

func init() {
	f[0], g[0] = 1, 1
	for i := 1; i < n; i++ {
		f[i] = f[i-1] * i % mod
		g[i] = qmi(f[i], mod-2, mod)
		x := i
		for j := 2; j <= x/j; j++ {
			if x%j == 0 {
				cnt := 0
				for x%j == 0 {
					cnt++
					x /= j
				}
				p[i] = append(p[i], cnt)
			}
		}
		if x > 1 {
			p[i] = append(p[i], 1)
		}
	}
}

func comb(n, k int) int {
	return (f[n] * g[k] % mod) * g[n-k] % mod
}

func waysToFillArray(queries [][]int) (ans []int) {
	for _, q := range queries {
		n, k := q[0], q[1]
		t := 1
		for _, x := range p[k] {
			t = t * comb(x+n-1, n-1) % mod
		}
		ans = append(ans, t)
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
