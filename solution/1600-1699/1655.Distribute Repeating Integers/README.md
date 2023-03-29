# [1655. 分配重复整数](https://leetcode.cn/problems/distribute-repeating-integers)

[English Version](/solution/1600-1699/1655.Distribute%20Repeating%20Integers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为&nbsp;<code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;，这个数组中至多有&nbsp;<code>50</code>&nbsp;个不同的值。同时你有 <code>m</code>&nbsp;个顾客的订单 <code>quantity</code>&nbsp;，其中，整数&nbsp;<code>quantity[i]</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;位顾客订单的数目。请你判断是否能将 <code>nums</code>&nbsp;中的整数分配给这些顾客，且满足：</p>

<ul>
	<li>第&nbsp;<code>i</code>&nbsp;位顾客 <strong>恰好&nbsp;</strong>有&nbsp;<code>quantity[i]</code>&nbsp;个整数。</li>
	<li>第&nbsp;<code>i</code>&nbsp;位顾客拿到的整数都是 <strong>相同的</strong>&nbsp;。</li>
	<li>每位顾客都满足上述两个要求。</li>
</ul>

<p>如果你可以分配 <code>nums</code>&nbsp;中的整数满足上面的要求，那么请返回&nbsp;<code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4], quantity = [2]
<b>输出：</b>false
<strong>解释：</strong>第 0 位顾客没办法得到两个相同的整数。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,3], quantity = [2]
<b>输出：</b>true
<b>解释：</b>第 0 位顾客得到 [3,3] 。整数 [1,2] 都没有被使用。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,2,2], quantity = [2,2]
<b>输出：</b>true
<b>解释：</b>第 0 位顾客得到 [1,1] ，第 1 位顾客得到 [2,2] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>m == quantity.length</code></li>
	<li><code>1 &lt;= m &lt;= 10</code></li>
	<li><code>1 &lt;= quantity[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums</code>&nbsp;中至多有&nbsp;<code>50</code>&nbsp;个不同的数字。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩动态规划 + 子集枚举**

我们先统计数组 $nums$ 中每个数字出现的次数，记录在哈希表 $cnt$ 中，然后将哈希表中的值存入数组 $arr$ 中，我们记数组 $arr$ 的长度为 $n$。

注意到数组 $quantity$ 的长度不超过 $10$，因此，我们可以用一个二进制数表示 $quantity$ 中的一个子集，即数字 $j$ 表示 $quantity$ 中的一个子集，其中 $j$ 的二进制表示中的第 $i$ 位为 $1$ 表示 $quantity$ 中的第 $i$ 个数字被选中，为 $0$ 表示第 $i$ 个数字未被选中。

我们可以预处理出数组 $s$，其中 $s[j]$ 表示 $quantity$ 中子集 $j$ 中所有数字的和。

接下来，我们定义 $f[i][j]$ 表示数组 $arr[0,..i-1]$ 中的数字能否成功分配给 $quantity$ 中的子集 $j$，其中 $i$ 的取值范围为 $[0,..n-1]$，而 $j$ 的取值范围为 $[0,2^m-1]$，其中 $m$ 为 $quantity$ 的长度。

考虑 $f[i][j]$，如果子集 $j$ 中存在一个子集 $k$，使得 $s[k] \leq arr[i]$，并且 $f[i-1][j \oplus k]$ 为真，那么 $f[i][j]$ 为真，否则 $f[i][j]$ 为假。

答案为 $f[n-1][2^m-1]$。

时间复杂度 $O(n \times 3^m)$，空间复杂度 $O(n \times 2^m)$。其中 $n$ 是数组 $nums$ 中不同整数的个数；而 $m$ 是数组 $quantity$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def canDistribute(self, nums: List[int], quantity: List[int]) -> bool:
        m = len(quantity)
        s = [0] * (1 << m)
        for i in range(1, 1 << m):
            for j in range(m):
                if i >> j & 1:
                    s[i] = s[i ^ (1 << j)] + quantity[j]
                    break
        cnt = Counter(nums)
        arr = list(cnt.values())
        n = len(arr)
        f = [[False] * (1 << m) for _ in range(n)]
        for i in range(n):
            f[i][0] = True
        for i, x in enumerate(arr):
            for j in range(1, 1 << m):
                if i and f[i - 1][j]:
                    f[i][j] = True
                    continue
                k = j
                while k:
                    ok1 = j == k if i == 0 else f[i - 1][j ^ k]
                    ok2 = s[k] <= x
                    if ok1 and ok2:
                        f[i][j] = True
                        break
                    k = (k - 1) & j
        return f[-1][-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean canDistribute(int[] nums, int[] quantity) {
        int m = quantity.length;
        int[] s = new int[1 << m];
        for (int i = 1; i < 1 << m; ++i) {
            for (int j = 0; j < m; ++j) {
                if ((i >> j & 1) != 0) {
                    s[i] = s[i ^ (1 << j)] + quantity[j];
                    break;
                }
            }
        }
        Map<Integer, Integer> cnt = new HashMap<>(50);
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        int n = cnt.size();
        int[] arr = new int[n];
        int i = 0;
        for (int x : cnt.values()) {
            arr[i++] = x;
        }
        boolean[][] f = new boolean[n][1 << m];
        for (i = 0; i < n; ++i) {
            f[i][0] = true;
        }
        for (i = 0; i < n; ++i) {
            for (int j = 1; j < 1 << m; ++j) {
                if (i > 0 && f[i - 1][j]) {
                    f[i][j] = true;
                    continue;
                }
                for (int k = j; k > 0; k = (k - 1) & j) {
                    boolean ok1 = i == 0 ? j == k : f[i - 1][j ^ k];
                    boolean ok2 = s[k] <= arr[i];
                    if (ok1 && ok2) {
                        f[i][j] = true;
                        break;
                    }
                }
            }
        }
        return f[n - 1][(1 << m) - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canDistribute(vector<int>& nums, vector<int>& quantity) {
        int m = quantity.size();
        int s[1 << m];
        memset(s, 0, sizeof(s));
        for (int i = 1; i < 1 << m; ++i) {
            for (int j = 0; j < m; ++j) {
                if (i >> j & 1) {
                    s[i] = s[i ^ (1 << j)] + quantity[j];
                    break;
                }
            }
        }
        unordered_map<int, int> cnt;
        for (int& x : nums) {
            ++cnt[x];
        }
        int n = cnt.size();
        vector<int> arr;
        for (auto& [_, x] : cnt) {
            arr.push_back(x);
        }
        bool f[n][1 << m];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < n; ++i) {
            f[i][0] = true;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < 1 << m; ++j) {
                if (i && f[i - 1][j]) {
                    f[i][j] = true;
                    continue;
                }
                for (int k = j; k; k = (k - 1) & j) {
                    bool ok1 = i == 0 ? j == k : f[i - 1][j ^ k];
                    bool ok2 = s[k] <= arr[i];
                    if (ok1 && ok2) {
                        f[i][j] = true;
                        break;
                    }
                }
            }
        }
        return f[n - 1][(1 << m) - 1];
    }
};
```

### **Go**

```go
func canDistribute(nums []int, quantity []int) bool {
	m := len(quantity)
	s := make([]int, 1<<m)
	for i := 1; i < 1<<m; i++ {
		for j := 0; j < m; j++ {
			if i>>j&1 == 1 {
				s[i] = s[i^(1<<j)] + quantity[j]
				break
			}
		}
	}
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	n := len(cnt)
	arr := make([]int, 0, n)
	for _, x := range cnt {
		arr = append(arr, x)
	}
	f := make([][]bool, n)
	for i := range f {
		f[i] = make([]bool, 1<<m)
		f[i][0] = true
	}
	for i := 0; i < n; i++ {
		for j := 0; j < 1<<m; j++ {
			if i > 0 && f[i-1][j] {
				f[i][j] = true
				continue
			}
			for k := j; k > 0; k = (k - 1) & j {
				ok1 := (i == 0 && j == k) || (i > 0 && f[i-1][j-k])
				ok2 := s[k] <= arr[i]
				if ok1 && ok2 {
					f[i][j] = true
					break
				}
			}
		}
	}
	return f[n-1][(1<<m)-1]
}
```

### **TypeScript**

```ts
function canDistribute(nums: number[], quantity: number[]): boolean {
    const m = quantity.length;
    const s: number[] = new Array(1 << m).fill(0);
    for (let i = 1; i < 1 << m; ++i) {
        for (let j = 0; j < m; ++j) {
            if ((i >> j) & 1) {
                s[i] = s[i ^ (1 << j)] + quantity[j];
                break;
            }
        }
    }
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    const n = cnt.size;
    const arr: number[] = [];
    for (const [_, v] of cnt) {
        arr.push(v);
    }
    const f: boolean[][] = new Array(n)
        .fill(false)
        .map(() => new Array(1 << m).fill(false));
    for (let i = 0; i < n; ++i) {
        f[i][0] = true;
    }
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < 1 << m; ++j) {
            if (i > 0 && f[i - 1][j]) {
                f[i][j] = true;
                continue;
            }
            for (let k = j; k > 0; k = (k - 1) & j) {
                const ok1: boolean =
                    (i == 0 && j == k) || (i > 0 && f[i - 1][j ^ k]);
                const ok2: boolean = s[k] <= arr[i];
                if (ok1 && ok2) {
                    f[i][j] = true;
                    break;
                }
            }
        }
    }
    return f[n - 1][(1 << m) - 1];
}
```

### **...**

```

```

<!-- tabs:end -->
