# [465. Optimal Account Balancing](https://leetcode.com/problems/optimal-account-balancing)

[中文文档](/solution/0400-0499/0465.Optimal%20Account%20Balancing/README.md)

## Description

<p>You are given an array of transactions <code>transactions</code> where <code>transactions[i] = [from<sub>i</sub>, to<sub>i</sub>, amount<sub>i</sub>]</code> indicates that the person with <code>ID = from<sub>i</sub></code> gave <code>amount<sub>i</sub> $</code> to the person with <code>ID = to<sub>i</sub></code>.</p>

<p>Return <em>the minimum number of transactions required to settle the debt</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> transactions = [[0,1,10],[2,0,5]]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.
Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.
Therefore, person #1 only need to give person #0 $4, and all debt is settled.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= transactions.length &lt;= 8</code></li>
	<li><code>transactions[i].length == 3</code></li>
	<li><code>0 &lt;= from<sub>i</sub>, to<sub>i</sub> &lt; 12</code></li>
	<li><code>from<sub>i</sub> != to<sub>i</sub></code></li>
	<li><code>1 &lt;= amount<sub>i</sub> &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def minTransfers(self, transactions: List[List[int]]) -> int:
        g = defaultdict(int)
        for f, t, x in transactions:
            g[f] -= x
            g[t] += x
        nums = [x for x in g.values() if x]
        m = len(nums)
        f = [inf] * (1 << m)
        f[0] = 0
        for i in range(1, 1 << m):
            s = 0
            for j, x in enumerate(nums):
                if i >> j & 1:
                    s += x
            if s == 0:
                f[i] = i.bit_count() - 1
                j = (i - 1) & i
                while j > 0:
                    f[i] = min(f[i], f[j] + f[i ^ j])
                    j = (j - 1) & i
        return f[-1]
```

```java
class Solution {
    public int minTransfers(int[][] transactions) {
        int[] g = new int[12];
        for (var t : transactions) {
            g[t[0]] -= t[2];
            g[t[1]] += t[2];
        }
        List<Integer> nums = new ArrayList<>();
        for (int x : g) {
            if (x != 0) {
                nums.add(x);
            }
        }
        int m = nums.size();
        int[] f = new int[1 << m];
        Arrays.fill(f, 1 << 29);
        f[0] = 0;
        for (int i = 1; i < 1 << m; ++i) {
            int s = 0;
            for (int j = 0; j < m; ++j) {
                if ((i >> j & 1) == 1) {
                    s += nums.get(j);
                }
            }
            if (s == 0) {
                f[i] = Integer.bitCount(i) - 1;
                for (int j = (i - 1) & i; j > 0; j = (j - 1) & i) {
                    f[i] = Math.min(f[i], f[j] + f[i ^ j]);
                }
            }
        }
        return f[(1 << m) - 1];
    }
}
```

```cpp
class Solution {
public:
    int minTransfers(vector<vector<int>>& transactions) {
        int g[12]{};
        for (auto& t : transactions) {
            g[t[0]] -= t[2];
            g[t[1]] += t[2];
        }
        vector<int> nums;
        for (int x : g) {
            if (x) {
                nums.push_back(x);
            }
        }
        int m = nums.size();
        int f[1 << m];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int i = 1; i < 1 << m; ++i) {
            int s = 0;
            for (int j = 0; j < m; ++j) {
                if (i >> j & 1) {
                    s += nums[j];
                }
            }
            if (s == 0) {
                f[i] = __builtin_popcount(i) - 1;
                for (int j = (i - 1) & i; j; j = (j - 1) & i) {
                    f[i] = min(f[i], f[j] + f[i ^ j]);
                }
            }
        }
        return f[(1 << m) - 1];
    }
};
```

```go
func minTransfers(transactions [][]int) int {
	g := [12]int{}
	for _, t := range transactions {
		g[t[0]] -= t[2]
		g[t[1]] += t[2]
	}
	nums := []int{}
	for _, x := range g {
		if x != 0 {
			nums = append(nums, x)
		}
	}
	m := len(nums)
	f := make([]int, 1<<m)
	for i := 1; i < 1<<m; i++ {
		f[i] = 1 << 29
		s := 0
		for j, x := range nums {
			if i>>j&1 == 1 {
				s += x
			}
		}
		if s == 0 {
			f[i] = bits.OnesCount(uint(i)) - 1
			for j := (i - 1) & i; j > 0; j = (j - 1) & i {
				f[i] = min(f[i], f[j]+f[i^j])
			}
		}
	}
	return f[1<<m-1]
}
```

```ts
function minTransfers(transactions: number[][]): number {
    const g: number[] = new Array(12).fill(0);
    for (const [f, t, x] of transactions) {
        g[f] -= x;
        g[t] += x;
    }
    const nums = g.filter(x => x !== 0);
    const m = nums.length;
    const f: number[] = new Array(1 << m).fill(1 << 29);
    f[0] = 0;
    for (let i = 1; i < 1 << m; ++i) {
        let s = 0;
        for (let j = 0; j < m; ++j) {
            if (((i >> j) & 1) === 1) {
                s += nums[j];
            }
        }
        if (s === 0) {
            f[i] = bitCount(i) - 1;
            for (let j = (i - 1) & i; j; j = (j - 1) & i) {
                f[i] = Math.min(f[i], f[j] + f[i ^ j]);
            }
        }
    }
    return f[(1 << m) - 1];
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- end -->
