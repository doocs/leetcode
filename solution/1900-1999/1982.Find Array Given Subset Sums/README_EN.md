# [1982. Find Array Given Subset Sums](https://leetcode.com/problems/find-array-given-subset-sums)

[中文文档](/solution/1900-1999/1982.Find%20Array%20Given%20Subset%20Sums/README.md)

## Description

<p>You are given an integer <code>n</code> representing the length of an unknown array that you are trying to recover. You are also given an array <code>sums</code> containing the values of all <code>2<sup>n</sup></code> <strong>subset sums</strong> of the unknown array (in no particular order).</p>

<p>Return <em>the array </em><code>ans</code><em> of length </em><code>n</code><em> representing the unknown array. If <strong>multiple</strong> answers exist, return <strong>any</strong> of them</em>.</p>

<p>An array <code>sub</code> is a <strong>subset</strong> of an array <code>arr</code> if <code>sub</code> can be obtained from <code>arr</code> by deleting some (possibly zero or all) elements of <code>arr</code>. The sum of the elements in <code>sub</code> is one possible <strong>subset sum</strong> of <code>arr</code>. The sum of an empty array is considered to be <code>0</code>.</p>

<p><strong>Note:</strong> Test cases are generated such that there will <strong>always</strong> be at least one correct answer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, sums = [-3,-2,-1,0,0,1,2,3]
<strong>Output:</strong> [1,2,-3]
<strong>Explanation: </strong>[1,2,-3] is able to achieve the given subset sums:
- []: sum is 0
- [1]: sum is 1
- [2]: sum is 2
- [1,2]: sum is 3
- [-3]: sum is -3
- [1,-3]: sum is -2
- [2,-3]: sum is -1
- [1,2,-3]: sum is 0
Note that any permutation of [1,2,-3] and also any permutation of [-1,-2,3] will also be accepted.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, sums = [0,0,0,0]
<strong>Output:</strong> [0,0]
<strong>Explanation:</strong> The only correct answer is [0,0].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 4, sums = [0,0,5,5,4,-1,4,9,9,-1,4,3,4,8,3,8]
<strong>Output:</strong> [0,-1,4,5]
<strong>Explanation:</strong> [0,-1,4,5] is able to achieve the given subset sums.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 15</code></li>
	<li><code>sums.length == 2<sup>n</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= sums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
from sortedcontainers import SortedList


class Solution:
    def recoverArray(self, n: int, sums: List[int]) -> List[int]:
        m = -min(sums)
        sl = SortedList(x + m for x in sums)
        sl.remove(0)
        ans = [sl[0]]
        for i in range(1, n):
            for j in range(1 << i):
                if j >> (i - 1) & 1:
                    s = sum(ans[k] for k in range(i) if j >> k & 1)
                    sl.remove(s)
            ans.append(sl[0])
        for i in range(1 << n):
            s = sum(ans[j] for j in range(n) if i >> j & 1)
            if s == m:
                for j in range(n):
                    if i >> j & 1:
                        ans[j] *= -1
                break
        return ans
```

```python
class Solution:
    def recoverArray(self, n: int, sums: List[int]) -> List[int]:
        sums.sort()
        ans = []
        for i in range(n, 0, -1):
            k = 1 << i
            d = sums[k - 1] - sums[k - 2]
            cnt = Counter(sums[:k])
            sums1, sums2 = [], []
            sign = 1
            for s in sums[:k]:
                if not cnt[s]:
                    continue
                cnt[s] -= 1
                cnt[s + d] -= 1
                sums1.append(s)
                sums2.append(s + d)
                if s + d == 0:
                    sign = -1
            ans.append(sign * d)
            sums = sums1 if sign == 1 else sums2
        return ans
```

### **Java**

```java
class Solution {
    public int[] recoverArray(int n, int[] sums) {
        int m = 1 << 30;
        for (int x : sums) {
            m = Math.min(m, x);
        }
        m = -m;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int x : sums) {
            tm.merge(x + m, 1, Integer::sum);
        }
        int[] ans = new int[n];
        if (tm.merge(0, -1, Integer::sum) == 0) {
            tm.remove(0);
        }
        ans[0] = tm.firstKey();
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < 1 << i; ++j) {
                if ((j >> (i - 1) & 1) == 1) {
                    int s = 0;
                    for (int k = 0; k < i; ++k) {
                        if (((j >> k) & 1) == 1) {
                            s += ans[k];
                        }
                    }
                    if (tm.merge(s, -1, Integer::sum) == 0) {
                        tm.remove(s);
                    }
                }
            }
            ans[i] = tm.firstKey();
        }
        for (int i = 0; i < 1 << n; ++i) {
            int s = 0;
            for (int j = 0; j < n; ++j) {
                if (((i >> j) & 1) == 1) {
                    s += ans[j];
                }
            }
            if (s == m) {
                for (int j = 0; j < n; ++j) {
                    if (((i >> j) & 1) == 1) {
                        ans[j] *= -1;
                    }
                }
                break;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] recoverArray(int n, int[] sums) {
        Arrays.sort(sums);
        int[] sums1 = new int[1 << n];
        int[] sums2 = new int[1 << n];
        Map<Integer, Integer> cnt = new HashMap<>();
        int[] ans = new int[n];
        for (int i = n; i > 0; --i) {
            int k = 1 << i;
            int d = sums[k - 1] - sums[k - 2];
            cnt.clear();
            for (int j = 0; j < k; ++j) {
                cnt.merge(sums[j], 1, Integer::sum);
            }
            int sign = 1;
            for (int j = 0, p = 0; j < k; ++j) {
                if (cnt.getOrDefault(sums[j], 0) == 0) {
                    continue;
                }
                cnt.merge(sums[j], -1, Integer::sum);
                cnt.merge(sums[j] + d, -1, Integer::sum);
                sums1[p] = sums[j];
                sums2[p++] = sums[j] + d;
                if (sums[j] + d == 0) {
                    sign = -1;
                }
            }
            ans[i - 1] = sign * d;
            System.arraycopy(sign == 1 ? sums1 : sums2, 0, sums, 0, k / 2);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> recoverArray(int n, vector<int>& sums) {
        int m = *min_element(sums.begin(), sums.end());
        m = -m;
        multiset<int> st;
        for (int x : sums) {
            st.insert(x + m);
        }
        st.erase(st.begin());
        vector<int> ans;
        ans.push_back(*st.begin());
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < 1 << i; ++j) {
                if (j >> (i - 1) & 1) {
                    int s = 0;
                    for (int k = 0; k < i; ++k) {
                        if (j >> k & 1) {
                            s += ans[k];
                        }
                    }
                    st.erase(st.find(s));
                }
            }
            ans.push_back(*st.begin());
        }
        for (int i = 0; i < 1 << n; ++i) {
            int s = 0;
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    s += ans[j];
                }
            }
            if (s == m) {
                for (int j = 0; j < n; ++j) {
                    if (i >> j & 1) {
                        ans[j] = -ans[j];
                    }
                }
                break;
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> recoverArray(int n, vector<int>& sums) {
        sort(sums.begin(), sums.end());
        vector<int> ans(n);
        unordered_map<int, int> cnt;
        for (int i = n; i; --i) {
            cnt.clear();
            int k = 1 << i;
            int d = sums[k - 1] - sums[k - 2];
            for (int j = 0; j < k; ++j) {
                cnt[sums[j]]++;
            }
            vector<int> sums1, sums2;
            int sign = 1;
            for (int j = 0; j < k; ++j) {
                if (cnt[sums[j]] == 0) {
                    continue;
                }
                --cnt[sums[j]];
                --cnt[sums[j] + d];
                sums1.push_back(sums[j]);
                sums2.push_back(sums[j] + d);
                if (sums2.back() == 0) {
                    sign = -1;
                }
            }
            ans[i - 1] = sign * d;
            for (int j = 0; j < k / 2; ++j) {
                sums[j] = sign == 1 ? sums1[j] : sums2[j];
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func recoverArray(n int, sums []int) []int {
	m := 0
	for _, x := range sums {
		m = min(m, x)
	}
	m = -m
	rbt := redblacktree.NewWithIntComparator()
	merge := func(key int, value int) {
		if v, ok := rbt.Get(key); ok {
			nxt := v.(int) + value
			if nxt == 0 {
				rbt.Remove(key)
			} else {
				rbt.Put(key, nxt)
			}
		} else {
			rbt.Put(key, value)
		}
	}
	for _, x := range sums {
		merge(x+m, 1)
	}
	ans := make([]int, n)
	merge(ans[0], -1)
	ans[0] = rbt.Left().Key.(int)
	for i := 1; i < n; i++ {
		for j := 0; j < 1<<i; j++ {
			if j>>(i-1)&1 == 1 {
				s := 0
				for k := 0; k < i; k++ {
					if j>>k&1 == 1 {
						s += ans[k]
					}
				}
				merge(s, -1)
			}
		}
		ans[i] = rbt.Left().Key.(int)
	}
	for i := 0; i < 1<<n; i++ {
		s := 0
		for j := 0; j < n; j++ {
			if i>>j&1 == 1 {
				s += ans[j]
			}
		}
		if s == m {
			for j := 0; j < n; j++ {
				if i>>j&1 == 1 {
					ans[j] = -ans[j]
				}
			}
			break
		}
	}
	return ans

}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func recoverArray(n int, sums []int) (ans []int) {
	sort.Ints(sums)
	for i := n; i > 0; i-- {
		k := 1 << i
		d := sums[k-1] - sums[k-2]
		cnt := map[int]int{}
		for _, s := range sums[:k] {
			cnt[s]++
		}
		sums1, sums2 := []int{}, []int{}
		sign := 1
		for _, s := range sums[:k] {
			if cnt[s] == 0 {
				continue
			}
			cnt[s]--
			cnt[s+d]--
			sums1 = append(sums1, s)
			sums2 = append(sums2, s+d)
			if s+d == 0 {
				sign = -1
			}
		}
		ans = append(ans, sign*d)
		if sign == -1 {
			sums1 = sums2
		}
		sums = sums1
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
