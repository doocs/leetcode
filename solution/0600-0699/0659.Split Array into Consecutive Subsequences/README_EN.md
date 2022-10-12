# [659. Split Array into Consecutive Subsequences](https://leetcode.com/problems/split-array-into-consecutive-subsequences)

[中文文档](/solution/0600-0699/0659.Split%20Array%20into%20Consecutive%20Subsequences/README.md)

## Description

<p>You are given an integer array <code>nums</code> that is <strong>sorted in non-decreasing order</strong>.</p>

<p>Determine if it is possible to split <code>nums</code> into <strong>one or more subsequences</strong> such that <strong>both</strong> of the following conditions are true:</p>

<ul>
	<li>Each subsequence is a <strong>consecutive increasing sequence</strong> (i.e. each integer is <strong>exactly one</strong> more than the previous integer).</li>
	<li>All subsequences have a length of <code>3</code><strong> or more</strong>.</li>
</ul>

<p>Return <code>true</code><em> if you can split </em><code>nums</code><em> according to the above conditions, or </em><code>false</code><em> otherwise</em>.</p>

<p>A <strong>subsequence</strong> of an array is a new array that is formed from the original array by deleting some (can be none) of the elements without disturbing the relative positions of the remaining elements. (i.e., <code>[1,3,5]</code> is a subsequence of <code>[<u>1</u>,2,<u>3</u>,4,<u>5</u>]</code> while <code>[1,3,2]</code> is not).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,3,4,5]
<strong>Output:</strong> true
<strong>Explanation:</strong> nums can be split into the following subsequences:
[<strong><u>1</u></strong>,<strong><u>2</u></strong>,<strong><u>3</u></strong>,3,4,5] --&gt; 1, 2, 3
[1,2,3,<strong><u>3</u></strong>,<strong><u>4</u></strong>,<strong><u>5</u></strong>] --&gt; 3, 4, 5
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,3,4,4,5,5]
<strong>Output:</strong> true
<strong>Explanation:</strong> nums can be split into the following subsequences:
[<strong><u>1</u></strong>,<strong><u>2</u></strong>,<strong><u>3</u></strong>,3,<strong><u>4</u></strong>,4,<strong><u>5</u></strong>,5] --&gt; 1, 2, 3, 4, 5
[1,2,3,<strong><u>3</u></strong>,4,<strong><u>4</u></strong>,5,<strong><u>5</u></strong>] --&gt; 3, 4, 5
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,4,5]
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible to split nums into consecutive increasing subsequences of length 3 or more.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>nums</code> is sorted in <strong>non-decreasing</strong> order.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
