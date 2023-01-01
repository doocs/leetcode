# [1296. 划分数组为连续数字的集合](https://leetcode.cn/problems/divide-array-in-sets-of-k-consecutive-numbers)

[English Version](/solution/1200-1299/1296.Divide%20Array%20in%20Sets%20of%20K%20Consecutive%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个正整数&nbsp;<code>k</code>，请你判断是否可以把这个数组划分成一些由&nbsp;<code>k</code>&nbsp;个连续数字组成的集合。<br />
如果可以，请返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,3,4,4,5,6], k = 4
<strong>输出：</strong>true
<strong>解释：</strong>数组可以分成 [1,2,3,4] 和 [3,4,5,6]。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
<strong>输出：</strong>true
<strong>解释：</strong>数组可以分成 [1,2,3] , [2,3,4] , [3,4,5] 和 [9,10,11]。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,3,2,2,1,1], k = 3
<strong>输出：</strong>true
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4], k = 3
<strong>输出：</strong>false
<strong>解释：</strong>数组不能分成几个大小为 3 的子数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong>此题目与 846 重复：<a href="https://leetcode.cn/problems/hand-of-straights/" target="_blank">https://leetcode.cn/problems/hand-of-straights/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 排序**

我们先用哈希表 `cnt` 统计数组 `nums` 中每个数字出现的次数，然后对数组 `nums` 进行排序。

接下来，我们遍历数组 `nums`，对于数组中的每个数字 $v$，如果 $v$ 在哈希表 `cnt` 中出现的次数不为 $0$，则我们枚举 $v$ 到 $v+k-1$ 的每个数字，如果这些数字在哈希表 `cnt` 中出现的次数都不为 $0$，则我们将这些数字的出现次数减 $1$，如果减 $1$ 后这些数字的出现次数为 $0$，则我们在哈希表 `cnt` 中删除这些数字。否则说明无法将数组划分成若干个长度为 $k$ 的子数组，返回 `false`。如果可以将数组划分成若干个长度为 $k$ 的子数组，则遍历结束后返回 `true`。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `nums` 的长度。

**方法二：有序集合**

我们也可以使用有序集合统计数组 `nums` 中每个数字出现的次数。

接下来，循环取出有序集合中的最小值 $v$，然后枚举 $v$ 到 $v+k-1$ 的每个数字，如果这些数字在有序集合中出现的次数都不为 $0$，则我们将这些数字的出现次数减 $1$，如果出现次数减 $1$ 后为 $0$，则将该数字从有序集合中删除，否则说明无法将数组划分成若干个长度为 $k$ 的子数组，返回 `false`。如果可以将数组划分成若干个长度为 $k$ 的子数组，则遍历结束后返回 `true`。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isPossibleDivide(self, nums: List[int], k: int) -> bool:
        cnt = Counter(nums)
        for v in sorted(nums):
            if cnt[v]:
                for x in range(v, v + k):
                    if cnt[x] == 0:
                        return False
                    cnt[x] -= 1
                    if cnt[x] == 0:
                        cnt.pop(x)
        return True
```

```python
from sortedcontainers import SortedDict


class Solution:
    def isPossibleDivide(self, nums: List[int], k: int) -> bool:
        if len(nums) % k != 0:
            return False
        sd = SortedDict()
        for h in nums:
            if h in sd:
                sd[h] += 1
            else:
                sd[h] = 1
        while sd:
            v = sd.peekitem(0)[0]
            for i in range(v, v + k):
                if i not in sd:
                    return False
                if sd[i] == 1:
                    sd.pop(i)
                else:
                    sd[i] -= 1
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : nums) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        Arrays.sort(nums);
        for (int v : nums) {
            if (cnt.containsKey(v)) {
                for (int x = v; x < v + k; ++x) {
                    if (!cnt.containsKey(x)) {
                        return false;
                    }
                    cnt.put(x, cnt.get(x) - 1);
                    if (cnt.get(x) == 0) {
                        cnt.remove(x);
                    }
                }
            }
        }
        return true;
    }
}
```

```java
class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) {
            return false;
        }
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int h : nums) {
            tm.put(h, tm.getOrDefault(h, 0) + 1);
        }
        while (!tm.isEmpty()) {
            int v = tm.firstKey();
            for (int i = v; i < v + k; ++i) {
                if (!tm.containsKey(i)) {
                    return false;
                }
                if (tm.get(i) == 1) {
                    tm.remove(i);
                } else {
                    tm.put(i, tm.get(i) - 1);
                }
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isPossibleDivide(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for (int& v : nums) ++cnt[v];
        sort(nums.begin(), nums.end());
        for (int& v : nums) {
            if (cnt.count(v)) {
                for (int x = v; x < v + k; ++x) {
                    if (!cnt.count(x)) {
                        return false;
                    }
                    if (--cnt[x] == 0) {
                        cnt.erase(x);
                    }
                }
            }
        }
        return true;
    }
};
```

```cpp
class Solution {
public:
    bool isPossibleDivide(vector<int>& nums, int k) {
        if (nums.size() % k != 0) return false;
        map<int, int> mp;
        for (int& h : nums) mp[h] += 1;
        while (!mp.empty()) {
            int v = mp.begin()->first;
            for (int i = v; i < v + k; ++i) {
                if (!mp.count(i)) return false;
                if (mp[i] == 1)
                    mp.erase(i);
                else
                    mp[i] -= 1;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func isPossibleDivide(nums []int, k int) bool {
	cnt := map[int]int{}
	for _, v := range nums {
		cnt[v]++
	}
	sort.Ints(nums)
	for _, v := range nums {
		if _, ok := cnt[v]; ok {
			for x := v; x < v+k; x++ {
				if _, ok := cnt[x]; !ok {
					return false
				}
				cnt[x]--
				if cnt[x] == 0 {
					delete(cnt, x)
				}
			}
		}
	}
	return true
}
```

```go
func isPossibleDivide(nums []int, k int) bool {
	if len(nums)%k != 0 {
		return false
	}
	m := treemap.NewWithIntComparator()
	for _, h := range nums {
		if v, ok := m.Get(h); ok {
			m.Put(h, v.(int)+1)
		} else {
			m.Put(h, 1)
		}
	}
	for !m.Empty() {
		v, _ := m.Min()
		for i := v.(int); i < v.(int)+k; i++ {
			if _, ok := m.Get(i); !ok {
				return false
			}
			if v, _ := m.Get(i); v.(int) == 1 {
				m.Remove(i)
			} else {
				m.Put(i, v.(int)-1)
			}
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
