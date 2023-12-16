# [60. Permutation Sequence](https://leetcode.com/problems/permutation-sequence)

[中文文档](/solution/0000-0099/0060.Permutation%20Sequence/README.md)

## Description

<p>The set <code>[1, 2, 3, ...,&nbsp;n]</code> contains a total of <code>n!</code> unique permutations.</p>

<p>By listing and labeling all of the permutations in order, we get the following sequence for <code>n = 3</code>:</p>

<ol>
	<li><code>&quot;123&quot;</code></li>
	<li><code>&quot;132&quot;</code></li>
	<li><code>&quot;213&quot;</code></li>
	<li><code>&quot;231&quot;</code></li>
	<li><code>&quot;312&quot;</code></li>
	<li><code>&quot;321&quot;</code></li>
</ol>

<p>Given <code>n</code> and <code>k</code>, return the <code>k<sup>th</sup></code> permutation sequence.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> n = 3, k = 3
<strong>Output:</strong> "213"
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> n = 4, k = 9
<strong>Output:</strong> "2314"
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> n = 3, k = 1
<strong>Output:</strong> "123"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 9</code></li>
	<li><code>1 &lt;= k &lt;= n!</code></li>
</ul>

## Solutions

**Solution 1: Enumeration**

We know that the set $[1,2,..n]$ has a total of $n!$ permutations. If we determine the first digit, the number of permutations that the remaining digits can form is $(n-1)!$.

Therefore, we enumerate each digit $i$. If $k$ is greater than the number of permutations after the current position is determined, then we can directly subtract this number; otherwise, it means that we have found the number at the current position.

For each digit $i$, where $0 \leq i < n$, the number of permutations that the remaining digits can form is $(n-i-1)!$, which we denote as $fact$. The numbers used in the process are recorded in `vis`.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getPermutation(self, n: int, k: int) -> str:
        ans = []
        vis = [False] * (n + 1)
        for i in range(n):
            fact = 1
            for j in range(1, n - i):
                fact *= j
            for j in range(1, n + 1):
                if not vis[j]:
                    if k > fact:
                        k -= fact
                    else:
                        ans.append(str(j))
                        vis[j] = True
                        break
        return ''.join(ans)
```

### **Java**

```java
class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder ans = new StringBuilder();
        boolean[] vis = new boolean[n + 1];
        for (int i = 0; i < n; ++i) {
            int fact = 1;
            for (int j = 1; j < n - i; ++j) {
                fact *= j;
            }
            for (int j = 1; j <= n; ++j) {
                if (!vis[j]) {
                    if (k > fact) {
                        k -= fact;
                    } else {
                        ans.append(j);
                        vis[j] = true;
                        break;
                    }
                }
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string getPermutation(int n, int k) {
        string ans;
        bitset<10> vis;
        for (int i = 0; i < n; ++i) {
            int fact = 1;
            for (int j = 1; j < n - i; ++j) fact *= j;
            for (int j = 1; j <= n; ++j) {
                if (vis[j]) continue;
                if (k > fact)
                    k -= fact;
                else {
                    ans += to_string(j);
                    vis[j] = 1;
                    break;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func getPermutation(n int, k int) string {
	ans := make([]byte, n)
	vis := make([]bool, n+1)
	for i := 0; i < n; i++ {
		fact := 1
		for j := 1; j < n-i; j++ {
			fact *= j
		}
		for j := 1; j <= n; j++ {
			if !vis[j] {
				if k > fact {
					k -= fact
				} else {
					ans[i] = byte('0' + j)
					vis[j] = true
					break
				}
			}
		}
	}
	return string(ans)
}
```

### **C#**

```cs
public class Solution {
    public string GetPermutation(int n, int k) {
        var ans = new StringBuilder();
        int vis = 0;
        for (int i = 0; i < n; ++i) {
            int fact = 1;
            for (int j = 1; j < n - i; ++j) {
                fact *= j;
            }
            for (int j = 1; j <= n; ++j) {
                if (((vis >> j) & 1) == 0) {
                    if (k > fact) {
                        k -= fact;
                    } else {
                        ans.Append(j);
                        vis |= 1 << j;
                        break;
                    }
                }
            }
        }
        return ans.ToString();
    }
}
```

### **Rust**

```rust
impl Solution {
    pub fn get_permutation(n: i32, k: i32) -> String {
        let mut k = k;
        let mut result = String::new();
        let mut factorial = vec![1; n as usize];
        for i in 1..n as usize {
            factorial[i] = factorial[i - 1] * i as i32;
        }
        let mut vis = vec![false; n as usize + 1];

        for i in 0..n as usize {
            let cnt = factorial[n as usize - i - 1];
            for j in 1..=n {
                if vis[j as usize] {
                    continue;
                }
                if k > cnt {
                    k -= cnt
                } else {
                    result.push_str(&j.to_string());
                    vis[j as usize] = true;
                    break;
                }
            }
        }

        result
    }
}
```

### **...**

```

```

<!-- tabs:end -->
