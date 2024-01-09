# [3003. Maximize the Number of Partitions After Operations](https://leetcode.com/problems/maximize-the-number-of-partitions-after-operations)

[中文文档](/solution/3000-3099/3003.Maximize%20the%20Number%20of%20Partitions%20After%20Operations/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>s</code> and an integer <code>k</code>.</p>

<p>You are to perform the following partitioning operations until <code>s</code> is <strong>empty</strong>:</p>

<ul>
	<li>Choose the <strong>longest</strong> <strong>prefix</strong> of <code>s</code> containing at most <code>k</code> <strong>distinct</strong> characters.</li>
	<li><strong>Delete</strong> the prefix from <code>s</code> and increase the number of partitions by one. The remaining characters (if any) in <code>s</code> maintain their initial order.</li>
</ul>

<p><strong>Before</strong> the operations, you are allowed to change <strong>at most</strong> <strong>one</strong> index in <code>s</code> to another lowercase English letter.</p>

<p>Return <em>an integer denoting the <strong>maximum</strong> number of resulting partitions after the operations by optimally choosing at most one index to change.</em></p>
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;accca&quot;, k = 2
<strong>Output:</strong> 3
<strong>Explanation: </strong>In this example, to maximize the number of resulting partitions, s[2] can be changed to &#39;b&#39;.
s becomes &quot;acbca&quot;.
The operations can now be performed as follows until s becomes empty:
- Choose the longest prefix containing at most 2 distinct characters, &quot;<u>ac</u>bca&quot;.
- Delete the prefix, and s becomes &quot;bca&quot;. The number of partitions is now 1.
- Choose the longest prefix containing at most 2 distinct characters, &quot;<u>bc</u>a&quot;.
- Delete the prefix, and s becomes &quot;a&quot;. The number of partitions is now 2.
- Choose the longest prefix containing at most 2 distinct characters, &quot;<u>a</u>&quot;.
- Delete the prefix, and s becomes empty. The number of partitions is now 3.
Hence, the answer is 3.
It can be shown that it is not possible to obtain more than 3 partitions.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabaab&quot;, k = 3
<strong>Output:</strong> 1
<strong>Explanation: </strong>In this example, to maximize the number of resulting partitions we can leave s as it is.
The operations can now be performed as follows until s becomes empty: 
- Choose the longest prefix containing at most 3 distinct characters, &quot;<u>aabaab</u>&quot;.
- Delete the prefix, and s becomes empty. The number of partitions becomes 1. 
Hence, the answer is 1. 
It can be shown that it is not possible to obtain more than 1 partition.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;xxyz&quot;, k = 1
<strong>Output:</strong> 4
<strong>Explanation:</strong> In this example, to maximize the number of resulting partitions, s[1] can be changed to &#39;a&#39;.
s becomes &quot;xayz&quot;.
The operations can now be performed as follows until s becomes empty:
- Choose the longest prefix containing at most 1 distinct character, &quot;<u>x</u>ayz&quot;.
- Delete the prefix, and s becomes &quot;ayz&quot;. The number of partitions is now 1.
- Choose the longest prefix containing at most 1 distinct character, &quot;<u>a</u>yz&quot;.
- Delete the prefix, and s becomes &quot;yz&quot;. The number of partitions is now 2.
- Choose the longest prefix containing at most 1 distinct character, &quot;<u>y</u>z&quot;.
- Delete the prefix, and s becomes &quot;z&quot;. The number of partitions is now 3.
- Choose the longest prefix containing at most 1 distinct character, &quot;<u>z</u>&quot;.
- Delete the prefix, and s becomes empty. The number of partitions is now 4.
Hence, the answer is 4.
It can be shown that it is not possible to obtain more than 4 partitions.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
	<li><code>1 &lt;= k &lt;= 26</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxPartitionsAfterOperations(self, s: str, k: int) -> int:
        @cache
        def dfs(i: int, cur: int, t: int) -> int:
            if i >= n:
                return 1
            v = 1 << (ord(s[i]) - ord("a"))
            nxt = cur | v
            if nxt.bit_count() > k:
                ans = dfs(i + 1, v, t) + 1
            else:
                ans = dfs(i + 1, nxt, t)
            if t:
                for j in range(26):
                    nxt = cur | (1 << j)
                    if nxt.bit_count() > k:
                        ans = max(ans, dfs(i + 1, 1 << j, 0) + 1)
                    else:
                        ans = max(ans, dfs(i + 1, nxt, 0))
            return ans

        n = len(s)
        return dfs(0, 0, 1)
```

### **Java**

```java
class Solution {
    private Map<List<Integer>, Integer> f = new HashMap<>();
    private String s;
    private int k;

    public int maxPartitionsAfterOperations(String s, int k) {
        this.s = s;
        this.k = k;
        return dfs(0, 0, 1);
    }

    private int dfs(int i, int cur, int t) {
        if (i >= s.length()) {
            return 1;
        }
        var key = List.of(i, cur, t);
        if (f.containsKey(key)) {
            return f.get(key);
        }
        int v = 1 << (s.charAt(i) - 'a');
        int nxt = cur | v;
        int ans = Integer.bitCount(nxt) > k ? dfs(i + 1, v, t) + 1 : dfs(i + 1, nxt, t);
        if (t > 0) {
            for (int j = 0; j < 26; ++j) {
                nxt = cur | (1 << j);
                if (Integer.bitCount(nxt) > k) {
                    ans = Math.max(ans, dfs(i + 1, 1 << j, 0) + 1);
                } else {
                    ans = Math.max(ans, dfs(i + 1, nxt, 0));
                }
            }
        }
        f.put(key, ans);
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxPartitionsAfterOperations(string s, int k) {
        int n = s.size();
        unordered_map<long long, int> f;
        function<int(int, int, int)> dfs = [&](int i, int cur, int t) {
            if (i >= n) {
                return 1;
            }
            long long key = (long long) i << 32 | cur << 1 | t;
            if (f.count(key)) {
                return f[key];
            }
            int v = 1 << (s[i] - 'a');
            int nxt = cur | v;
            int ans = __builtin_popcount(nxt) > k ? dfs(i + 1, v, t) + 1 : dfs(i + 1, nxt, t);
            if (t) {
                for (int j = 0; j < 26; ++j) {
                    nxt = cur | (1 << j);
                    if (__builtin_popcount(nxt) > k) {
                        ans = max(ans, dfs(i + 1, 1 << j, 0) + 1);
                    } else {
                        ans = max(ans, dfs(i + 1, nxt, 0));
                    }
                }
            }
            return f[key] = ans;
        };
        return dfs(0, 0, 1);
    }
};
```

### **Go**

```go
func maxPartitionsAfterOperations(s string, k int) int {
	n := len(s)
	type tuple struct{ i, cur, t int }
	f := map[tuple]int{}
	var dfs func(i, cur, t int) int
	dfs = func(i, cur, t int) int {
		if i >= n {
			return 1
		}
		key := tuple{i, cur, t}
		if v, ok := f[key]; ok {
			return v
		}
		v := 1 << (s[i] - 'a')
		nxt := cur | v
		var ans int
		if bits.OnesCount(uint(nxt)) > k {
			ans = dfs(i+1, v, t) + 1
		} else {
			ans = dfs(i+1, nxt, t)
		}
		if t > 0 {
			for j := 0; j < 26; j++ {
				nxt = cur | (1 << j)
				if bits.OnesCount(uint(nxt)) > k {
					ans = max(ans, dfs(i+1, 1<<j, 0)+1)
				} else {
					ans = max(ans, dfs(i+1, nxt, 0))
				}
			}
		}
		f[key] = ans
		return ans
	}
	return dfs(0, 0, 1)
}
```

### **TypeScript**

```ts
function maxPartitionsAfterOperations(s: string, k: number): number {
    const n = s.length;
    const f: Map<bigint, number> = new Map();
    const dfs = (i: number, cur: number, t: number): number => {
        if (i >= n) {
            return 1;
        }
        const key = (BigInt(i) << 27n) | (BigInt(cur) << 1n) | BigInt(t);
        if (f.has(key)) {
            return f.get(key)!;
        }
        const v = 1 << (s.charCodeAt(i) - 97);
        let nxt = cur | v;
        let ans = 0;
        if (bitCount(nxt) > k) {
            ans = dfs(i + 1, v, t) + 1;
        } else {
            ans = dfs(i + 1, nxt, t);
        }
        if (t) {
            for (let j = 0; j < 26; ++j) {
                nxt = cur | (1 << j);
                if (bitCount(nxt) > k) {
                    ans = Math.max(ans, dfs(i + 1, 1 << j, 0) + 1);
                } else {
                    ans = Math.max(ans, dfs(i + 1, nxt, 0));
                }
            }
        }
        f.set(key, ans);
        return ans;
    };
    return dfs(0, 0, 1);
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

### **...**

```

```

<!-- tabs:end -->
