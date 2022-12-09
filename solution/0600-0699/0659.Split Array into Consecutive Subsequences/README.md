# [659. 分割数组为连续子序列](https://leetcode.cn/problems/split-array-into-consecutive-subsequences)

[English Version](/solution/0600-0699/0659.Split%20Array%20into%20Consecutive%20Subsequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个按 <strong>非递减顺序</strong> 排列的整数数组 <code>nums</code> 。</p>

<p>请你判断是否能在将 <code>nums</code> 分割成 <strong>一个或多个子序列</strong> 的同时满足下述 <strong>两个</strong> 条件：</p>

<div class="original__bRMd">
<div>
<ul>
	<li>每个子序列都是一个 <strong>连续递增序列</strong>（即，每个整数 <strong>恰好</strong> 比前一个整数大 <strong>1</strong> ）。</li>
	<li>所有子序列的长度 <strong>至少</strong> 为 <code>3</code><strong> </strong>。</li>
</ul>

<p>如果可以分割 <code>nums</code> 并满足上述条件，则返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
</div>
</div>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,3,4,5]
<strong>输出：</strong>true
<strong>解释：</strong>nums 可以分割成以下子序列：
[<em><strong>1</strong></em>,<em><strong>2</strong></em>,<em><strong>3</strong></em>,3,4,5] --&gt; 1, 2, 3
[1,2,3,<em><strong>3</strong></em>,<em><strong>4</strong></em>,<em><strong>5</strong></em>] --&gt; 3, 4, 5
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,3,4,4,5,5]
<strong>输出：</strong>true
<strong>解释：</strong>nums 可以分割成以下子序列：
[<em><strong>1</strong></em>,<em><strong>2</strong></em>,<em><strong>3</strong></em>,3,<em><strong>4</strong></em>,4,<em><strong>5</strong></em>,5] --&gt; 1, 2, 3, 4, 5
[1,2,3,<em><strong>3</strong></em>,4,<em><strong>4</strong></em>,5,<em><strong>5</strong></em>] --&gt; 3, 4, 5
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4,4,5]
<strong>输出：</strong>false
<strong>解释：</strong>无法将 nums 分割成长度至少为 3 的连续递增子序列。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>nums</code> 按非递减顺序排列</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 优先队列（小根堆）**

由于题目中的子序列是由连续整数组成的，因此，只要知道子序列的最后一个数以及子序列的长度，就能够确定子序列。

我们可以使用哈希表存储每个子序列的最后一个数，使用优先队列存储当前数作为子序列的末尾时，子序列的长度。我们要优先选择长度较短的子序列，因此使用小根堆。

遍历数组 `nums`，对于当前遍历到的数 `num`，如果 `num` 不能加入到任何子序列中，那么我们就创建一个新的子序列，长度为 1；否则，我们就将 `num` 加入到某个子序列中，具体的子序列是哪个呢？我们可以从 `num - 1` 对应的子序列中取出一个长度最短的子序列，将 `num` 加入到该子序列中，然后将该子序列的最后一个数更新为 `num`，同时将该子序列的长度加 1。

如果我们遍历完数组 `nums`，优先队列中所有的子序列的长度都不小于 3，那么我们就可以将数组 `nums` 分割成若干个子序列，否则，我们就无法将数组 `nums` 分割成若干个子序列。

时间复杂度 $O(n\log n)$，其中 $n$ 是数组 `nums` 的长度。空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isPossible(self, nums: List[int]) -> bool:
        d = defaultdict(list)
        for v in nums:
            if h := d[v - 1]:
                heappush(d[v], heappop(h) + 1)
            else:
                heappush(d[v], 1)
        return all(not v or v and v[0] > 2 for v in d.values())
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> d = new HashMap<>();
        for (int v : nums) {
            if (d.containsKey(v - 1)) {
                var q = d.get(v - 1);
                d.computeIfAbsent(v, k -> new PriorityQueue<>()).offer(q.poll() + 1);
                if (q.isEmpty()) {
                    d.remove(v - 1);
                }
            } else {
                d.computeIfAbsent(v, k -> new PriorityQueue<>()).offer(1);
            }
        }
        for (var v : d.values()) {
            if (v.peek() < 3) {
                return false;
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
    bool isPossible(vector<int>& nums) {
        unordered_map<int, priority_queue<int, vector<int>, greater<int>>> d;
        for (int v : nums) {
            if (d.count(v - 1)) {
                auto& q = d[v - 1];
                d[v].push(q.top() + 1);
                q.pop();
                if (q.empty()) {
                    d.erase(v - 1);
                }
            } else {
                d[v].push(1);
            }
        }
        for (auto& [_, v] : d) {
            if (v.top() < 3) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func isPossible(nums []int) bool {
	d := map[int]*hp{}
	for _, v := range nums {
		if d[v] == nil {
			d[v] = new(hp)
		}
		if h := d[v-1]; h != nil {
			heap.Push(d[v], heap.Pop(h).(int)+1)
			if h.Len() == 0 {
				delete(d, v-1)
			}
		} else {
			heap.Push(d[v], 1)
		}
	}
	for _, q := range d {
		if q.IntSlice[0] < 3 {
			return false
		}
	}
	return true
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

### **...**

```

```

<!-- tabs:end -->
