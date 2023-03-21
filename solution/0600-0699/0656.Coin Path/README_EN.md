# [656. Coin Path](https://leetcode.com/problems/coin-path)

[中文文档](/solution/0600-0699/0656.Coin%20Path/README.md)

## Description

<p>You are given an integer array <code>coins</code> (<strong>1-indexed</strong>) of length <code>n</code> and an integer <code>maxJump</code>. You can jump to any index <code>i</code> of the array <code>coins</code> if <code>coins[i] != -1</code> and you have to pay <code>coins[i]</code> when you visit index <code>i</code>. In addition to that, if you are currently at index <code>i</code>, you can only jump to any index <code>i + k</code> where <code>i + k &lt;= n</code> and <code>k</code> is a value in the range <code>[1, maxJump]</code>.</p>

<p>You are initially positioned at index <code>1</code> (<code>coins[1]</code> is not <code>-1</code>). You want to find the path that reaches index n with the minimum cost.</p>

<p>Return an integer array of the indices that you will visit in order so that you can reach index n with the minimum cost. If there are multiple paths with the same cost, return the <strong>lexicographically smallest</strong> such path. If it is not possible to reach index n, return an empty array.</p>

<p>A path <code>p1 = [Pa<sub>1</sub>, Pa<sub>2</sub>, ..., Pa<sub>x</sub>]</code> of length <code>x</code> is <strong>lexicographically smaller</strong> than <code>p2 = [Pb<sub>1</sub>, Pb<sub>2</sub>, ..., Pb<sub>x</sub>]</code> of length <code>y</code>, if and only if at the first <code>j</code> where <code>Pa<sub>j</sub></code> and <code>Pb<sub>j</sub></code> differ, <code>Pa<sub>j</sub> &lt; Pb<sub>j</sub></code>; when no such <code>j</code> exists, then <code>x &lt; y</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> coins = [1,2,4,-1,2], maxJump = 2
<strong>Output:</strong> [1,3,5]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> coins = [1,2,4,-1,2], maxJump = 1
<strong>Output:</strong> []
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= coins.length &lt;= 1000</code></li>
	<li><code>-1 &lt;= coins[i] &lt;= 100</code></li>
	<li><code>coins[1] != -1</code></li>
	<li><code>1 &lt;= maxJump &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def cheapestJump(self, coins: List[int], maxJump: int) -> List[int]:
        if coins[-1] == -1:
            return []
        n = len(coins)
        f = [inf] * n
        f[-1] = coins[-1]
        for i in range(n - 2, -1, -1):
            if coins[i] != -1:
                for j in range(i + 1, min(n, i + maxJump + 1)):
                    if f[i] > f[j] + coins[i]:
                        f[i] = f[j] + coins[i]
        if f[0] == inf:
            return []
        ans = []
        s = f[0]
        for i in range(n):
            if f[i] == s:
                s -= coins[i]
                ans.append(i + 1)
        return ans
```

### **Java**

```java
class Solution {
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        int n = coins.length;
        List<Integer> ans = new ArrayList<>();
        if (coins[n - 1] == -1) {
            return ans;
        }
        int[] f = new int[n];
        final int inf = 1 << 30;
        Arrays.fill(f, inf);
        f[n - 1] = coins[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (coins[i] != -1) {
                for (int j = i + 1; j < Math.min(n, i + maxJump + 1); ++j) {
                    if (f[i] > f[j] + coins[i]) {
                        f[i] = f[j] + coins[i];
                    }
                }
            }
        }
        if (f[0] == inf) {
            return ans;
        }
        for (int i = 0, s = f[0]; i < n; ++i) {
            if (f[i] == s) {
                s -= coins[i];
                ans.add(i + 1);
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
    vector<int> cheapestJump(vector<int>& coins, int maxJump) {
        int n = coins.size();
        vector<int> ans;
        if (coins[n - 1] == -1) {
            return ans;
        }
        int f[n];
        const int inf = 1 << 30;
        f[n - 1] = coins[n - 1];
        for (int i = n - 2; ~i; --i) {
            f[i] = inf;
            if (coins[i] != -1) {
                for (int j = i + 1; j < min(n, i + maxJump + 1); ++j) {
                    f[i] = min(f[i], f[j] + coins[i]);
                }
            }
        }
        if (f[0] == inf) {
            return ans;
        }
        for (int i = 0, s = f[0]; i < n; ++i) {
            if (f[i] == s) {
                s -= coins[i];
                ans.push_back(i + 1);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func cheapestJump(coins []int, maxJump int) (ans []int) {
	n := len(coins)
	if coins[n-1] == -1 {
		return
	}
	f := make([]int, n)
	f[n-1] = coins[n-1]
	const inf = 1 << 30
	for i := n - 2; i >= 0; i-- {
		f[i] = inf
		if coins[i] != -1 {
			for j := i + 1; j < n && j < i+maxJump+1; j++ {
				if f[i] > f[j]+coins[i] {
					f[i] = f[j] + coins[i]
				}
			}
		}
	}
	if f[0] == inf {
		return
	}
	for i, s := 0, f[0]; i < n; i++ {
		if f[i] == s {
			s -= coins[i]
			ans = append(ans, i+1)
		}
	}
	return
}
```

### **TypeScript**

```ts
function cheapestJump(coins: number[], maxJump: number): number[] {
    const n = coins.length;
    const ans: number[] = [];
    if (coins[n - 1] == -1) {
        return ans;
    }
    const inf = 1 << 30;
    const f: number[] = new Array(n).fill(inf);
    f[n - 1] = coins[n - 1];
    for (let i = n - 2; i >= 0; --i) {
        if (coins[i] !== -1) {
            for (let j = i + 1; j < Math.min(n, i + maxJump + 1); ++j) {
                f[i] = Math.min(f[i], f[j] + coins[i]);
            }
        }
    }
    if (f[0] === inf) {
        return ans;
    }
    for (let i = 0, s = f[0]; i < n; ++i) {
        if (f[i] == s) {
            s -= coins[i];
            ans.push(i + 1);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
