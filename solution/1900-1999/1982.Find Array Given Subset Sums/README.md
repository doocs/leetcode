# [1982. 从子集的和还原数组](https://leetcode.cn/problems/find-array-given-subset-sums)

[English Version](/solution/1900-1999/1982.Find%20Array%20Given%20Subset%20Sums/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>存在一个未知数组需要你进行还原，给你一个整数 <code>n</code> 表示该数组的长度。另给你一个数组 <code>sums</code> ，由未知数组中全部 <code>2<sup>n</sup></code> 个 <strong>子集的和</strong> 组成（子集中的元素没有特定的顺序）。</p>

<p>返回一个长度为 <code>n</code> 的数组<em> </em><code>ans</code><em> </em>表示还原得到的未知数组。如果存在 <strong>多种</strong> 答案，只需返回其中 <strong>任意一个</strong> 。</p>

<p>如果可以由数组 <code>arr</code> 删除部分元素（也可能不删除或全删除）得到数组 <code>sub</code> ，那么数组 <code>sub</code> 就是数组 <code>arr</code> 的一个<strong> 子集</strong> 。<code>sub</code> 的元素之和就是 <code>arr</code> 的一个 <strong>子集的和</strong> 。一个空数组的元素之和为 <code>0</code> 。</p>

<p><strong>注意：</strong>生成的测试用例将保证至少存在一个正确答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3, sums = [-3,-2,-1,0,0,1,2,3]
<strong>输出：</strong>[1,2,-3]
<strong>解释：</strong>[1,2,-3] 能够满足给出的子集的和：
- []：和是 0
- [1]：和是 1
- [2]：和是 2
- [1,2]：和是 3
- [-3]：和是 -3
- [1,-3]：和是 -2
- [2,-3]：和是 -1
- [1,2,-3]：和是 0
注意，[1,2,-3] 的任何排列和 [-1,-2,3] 的任何排列都会被视作正确答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2, sums = [0,0,0,0]
<strong>输出：</strong>[0,0]
<strong>解释：</strong>唯一的正确答案是 [0,0] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 4, sums = [0,0,5,5,4,-1,4,9,9,-1,4,3,4,8,3,8]
<strong>输出：</strong>[0,-1,4,5]
<strong>解释：</strong>[0,-1,4,5] 能够满足给出的子集的和。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 15</code></li>
	<li><code>sums.length == 2<sup>n</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= sums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
