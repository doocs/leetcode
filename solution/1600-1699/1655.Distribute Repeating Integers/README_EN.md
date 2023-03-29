# [1655. Distribute Repeating Integers](https://leetcode.com/problems/distribute-repeating-integers)

[中文文档](/solution/1600-1699/1655.Distribute%20Repeating%20Integers/README.md)

## Description

<p>You are given an array of <code>n</code> integers, <code>nums</code>, where there are at most <code>50</code> unique values in the array. You are also given an array of <code>m</code> customer order quantities, <code>quantity</code>, where <code>quantity[i]</code> is the amount of integers the <code>i<sup>th</sup></code> customer ordered. Determine if it is possible to distribute <code>nums</code> such that:</p>

<ul>
	<li>The <code>i<sup>th</sup></code> customer gets <strong>exactly</strong> <code>quantity[i]</code> integers,</li>
	<li>The integers the <code>i<sup>th</sup></code> customer gets are <strong>all equal</strong>, and</li>
	<li>Every customer is satisfied.</li>
</ul>

<p>Return <code>true</code><em> if it is possible to distribute </em><code>nums</code><em> according to the above conditions</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4], quantity = [2]
<strong>Output:</strong> false
<strong>Explanation:</strong> The 0<sup>th</sup> customer cannot be given two different integers.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,3], quantity = [2]
<strong>Output:</strong> true
<strong>Explanation:</strong> The 0<sup>th</sup> customer is given [3,3]. The integers [1,2] are not used.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2,2], quantity = [2,2]
<strong>Output:</strong> true
<strong>Explanation:</strong> The 0<sup>th</sup> customer is given [1,1], and the 1st customer is given [2,2].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>m == quantity.length</code></li>
	<li><code>1 &lt;= m &lt;= 10</code></li>
	<li><code>1 &lt;= quantity[i] &lt;= 10<sup>5</sup></code></li>
	<li>There are at most <code>50</code> unique values in <code>nums</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
