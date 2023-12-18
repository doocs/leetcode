# [1711. Count Good Meals](https://leetcode.com/problems/count-good-meals)

[中文文档](/solution/1700-1799/1711.Count%20Good%20Meals/README.md)

## Description

<p>A <strong>good meal</strong> is a meal that contains <strong>exactly two different food items</strong> with a sum of deliciousness equal to a power of two.</p>

<p>You can pick <strong>any</strong> two different foods to make a good meal.</p>

<p>Given an array of integers <code>deliciousness</code> where <code>deliciousness[i]</code> is the deliciousness of the <code>i<sup>​​​​​​th</sup>​​​​</code>​​​​ item of food, return <em>the number of different <strong>good meals</strong> you can make from this list modulo</em> <code>10<sup>9</sup> + 7</code>.</p>

<p>Note that items with different indices are considered different even if they have the same deliciousness value.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> deliciousness = [1,3,5,7,9]
<strong>Output:</strong> 4
<strong>Explanation: </strong>The good meals are (1,3), (1,7), (3,5) and, (7,9).
Their respective sums are 4, 8, 8, and 16, all of which are powers of 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> deliciousness = [1,1,1,3,3,3,7]
<strong>Output:</strong> 15
<strong>Explanation: </strong>The good meals are (1,1) with 3 ways, (1,3) with 9 ways, and (1,7) with 3 ways.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= deliciousness.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= deliciousness[i] &lt;= 2<sup>20</sup></code></li>
</ul>

## Solutions

**Solution 1: Hash Table + Enumeration of Powers of Two**

According to the problem, we need to count the number of combinations in the array where the sum of two numbers is a power of $2$. Directly enumerating all combinations has a time complexity of $O(n^2)$, which will definitely time out.

We can traverse the array and use a hash table $cnt$ to maintain the number of occurrences of each element $d$ in the array.

For each element, we enumerate the powers of two $s$ as the sum of two numbers from small to large, and add the number of occurrences of $s - d$ in the hash table to the answer. Then increase the number of occurrences of the current element $d$ by one.

After the traversal ends, return the answer.

The time complexity is $O(n \times \log M)$, where $n$ is the length of the array `deliciousness`, and $M$ is the upper limit of the elements. For this problem, the upper limit $M=2^{20}$.

We can also use a hash table $cnt$ to count the number of occurrences of each element in the array first.

Then enumerate the powers of two $s$ as the sum of two numbers from small to large. For each $s$, traverse each key-value pair $(a, m)$ in the hash table. If $s - a$ is also in the hash table, and $s - a \neq a$, then add $m \times cnt[s - a]$ to the answer; if $s - a = a$, then add $m \times (m - 1)$ to the answer.

Finally, divide the answer by $2$, modulo $10^9 + 7$, and return.

The time complexity is the same as the method above.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countPairs(self, deliciousness: List[int]) -> int:
        mod = 10**9 + 7
        mx = max(deliciousness) << 1
        cnt = Counter()
        ans = 0
        for d in deliciousness:
            s = 1
            while s <= mx:
                ans = (ans + cnt[s - d]) % mod
                s <<= 1
            cnt[d] += 1
        return ans
```

```python
class Solution:
    def countPairs(self, deliciousness: List[int]) -> int:
        mod = 10**9 + 7
        cnt = Counter(deliciousness)
        ans = 0
        for i in range(22):
            s = 1 << i
            for a, m in cnt.items():
                if (b := s - a) in cnt:
                    ans += m * (m - 1) if a == b else m * cnt[b]
        return (ans >> 1) % mod
```

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countPairs(int[] deliciousness) {
        int mx = Arrays.stream(deliciousness).max().getAsInt() << 1;
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int d : deliciousness) {
            for (int s = 1; s <= mx; s <<= 1) {
                ans = (ans + cnt.getOrDefault(s - d, 0)) % MOD;
            }
            cnt.merge(d, 1, Integer::sum);
        }
        return ans;
    }
}
```

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countPairs(int[] deliciousness) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int d : deliciousness) {
            cnt.put(d, cnt.getOrDefault(d, 0) + 1);
        }
        long ans = 0;
        for (int i = 0; i < 22; ++i) {
            int s = 1 << i;
            for (var x : cnt.entrySet()) {
                int a = x.getKey(), m = x.getValue();
                int b = s - a;
                if (!cnt.containsKey(b)) {
                    continue;
                }
                ans += 1L * m * (a == b ? m - 1 : cnt.get(b));
            }
        }
        ans >>= 1;
        return (int) (ans % MOD);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int countPairs(vector<int>& deliciousness) {
        int mx = *max_element(deliciousness.begin(), deliciousness.end()) << 1;
        unordered_map<int, int> cnt;
        int ans = 0;
        for (auto& d : deliciousness) {
            for (int s = 1; s <= mx; s <<= 1) {
                ans = (ans + cnt[s - d]) % mod;
            }
            ++cnt[d];
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int countPairs(vector<int>& deliciousness) {
        unordered_map<int, int> cnt;
        for (int& d : deliciousness) ++cnt[d];
        long long ans = 0;
        for (int i = 0; i < 22; ++i) {
            int s = 1 << i;
            for (auto& [a, m] : cnt) {
                int b = s - a;
                if (!cnt.count(b)) continue;
                ans += 1ll * m * (a == b ? (m - 1) : cnt[b]);
            }
        }
        ans >>= 1;
        return ans % mod;
    }
};
```

### **Go**

```go
func countPairs(deliciousness []int) (ans int) {
	mx := slices.Max(deliciousness) << 1
	const mod int = 1e9 + 7
	cnt := map[int]int{}
	for _, d := range deliciousness {
		for s := 1; s <= mx; s <<= 1 {
			ans = (ans + cnt[s-d]) % mod
		}
		cnt[d]++
	}
	return
}
```

```go
func countPairs(deliciousness []int) (ans int) {
	cnt := map[int]int{}
	for _, d := range deliciousness {
		cnt[d]++
	}
	const mod int = 1e9 + 7
	for i := 0; i < 22; i++ {
		s := 1 << i
		for a, m := range cnt {
			b := s - a
			if n, ok := cnt[b]; ok {
				if a == b {
					ans += m * (m - 1)
				} else {
					ans += m * n
				}
			}
		}
	}
	ans >>= 1
	return ans % mod
}
```

### **...**

```

```

<!-- tabs:end -->
