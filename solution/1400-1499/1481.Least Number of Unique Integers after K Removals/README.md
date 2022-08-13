# [1481. 不同整数的最少数目](https://leetcode.cn/problems/least-number-of-unique-integers-after-k-removals)

[English Version](/solution/1400-1499/1481.Least%20Number%20of%20Unique%20Integers%20after%20K%20Removals/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>arr</code> 和一个整数 <code>k</code> 。现需要从数组中恰好移除 <code>k</code> 个元素，请找出移除后数组中不同整数的最少数目。</p>

<ol>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [5,5,4], k = 1
<strong>输出：</strong>1
<strong>解释：</strong>移除 1 个 4 ，数组中只剩下 5 一种整数。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [4,3,1,1,3,3,2], k = 3
<strong>输出：</strong>2
<strong>解释：</strong>先移除 4、2 ，然后再移除两个 1 中的任意 1 个或者三个 3 中的任意 1 个，最后剩下 1 和 3 两种整数。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length&nbsp;&lt;= 10^5</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10^9</code></li>
	<li><code>0 &lt;= k&nbsp;&lt;= arr.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findLeastNumOfUniqueInts(self, arr: List[int], k: int) -> int:
        counter = Counter(arr)
        t = sorted(counter.items(), key=lambda x: x[1])
        for v, cnt in t:
            if k >= cnt:
                k -= cnt
                counter.pop(v)
            else:
                break
        return len(counter)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int v : arr) {
            counter.put(v, counter.getOrDefault(v, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> t = new ArrayList<>(counter.entrySet());
        Collections.sort(t, Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> e : t) {
            int v = e.getKey();
            int cnt = e.getValue();
            if (k >= cnt) {
                k -= cnt;
                counter.remove(v);
            } else {
                break;
            }
        }
        return counter.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findLeastNumOfUniqueInts(vector<int>& arr, int k) {
        unordered_map<int, int> counter;
        for (int v : arr) ++counter[v];
        vector<pair<int, int>> t(counter.begin(), counter.end());
        sort(t.begin(), t.end(), [](const auto& a, const auto& b) { return a.second < b.second; });
        for (auto [v, cnt] : t) {
            if (k >= cnt) {
                k -= cnt;
                counter.erase(v);
            } else
                break;
        }
        return counter.size();
    }
};
```

### **Go**

```go
func findLeastNumOfUniqueInts(arr []int, k int) int {
	counter := make(map[int]int)
	for _, v := range arr {
		counter[v]++
	}
	var t [][]int
	for v, cnt := range counter {
		t = append(t, []int{v, cnt})
	}
	sort.Slice(t, func(i, j int) bool {
		return t[i][1] < t[j][1]
	})
	for _, e := range t {
		v, cnt := e[0], e[1]
		if k >= cnt {
			k -= cnt
			delete(counter, v)
		} else {
			break
		}
	}
	return len(counter)
}
```

### **...**

```

```

<!-- tabs:end -->
