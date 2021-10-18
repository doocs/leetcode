# [220. Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii)

[中文文档](/solution/0200-0299/0220.Contains%20Duplicate%20III/README.md)

## Description

<p>Given an integer array <code>nums</code> and two integers <code>k</code> and <code>t</code>, return <code>true</code> if there are <strong>two distinct indices</strong> <code>i</code> and <code>j</code> in the array such that <code>abs(nums[i] - nums[j]) &lt;= t</code> and <code>abs(i - j) &lt;= k</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,3,1], k = 3, t = 0
<strong>Output:</strong> true
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [1,0,1,1], k = 1, t = 2
<strong>Output:</strong> true
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> nums = [1,5,9,1,5,9], k = 2, t = 3
<strong>Output:</strong> false
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= t &lt;= 2<sup>31</sup> - 1</code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
from sortedcontainers import SortedSet


class Solution:
    def containsNearbyAlmostDuplicate(self, nums: List[int], k: int, t: int) -> bool:
        s = SortedSet()
        for i, num in enumerate(nums):
            idx = s.bisect_left(num - t)
            if 0 <= idx < len(s) and s[idx] <= num + t:
                return True
            s.add(num)
            if i >= k:
                s.remove(nums[i - k])
        return False
```

### **Java**

```java
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> ts = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            Long x = ts.ceiling((long) nums[i] - (long) t);
            if (x != null && x <= (long) nums[i] + (long) t) {
                return true;
            }
            ts.add((long) nums[i]);
            if (i >= k) {
                ts.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool containsNearbyAlmostDuplicate(vector<int>& nums, int k, int t) {
        set<long> s;
        for (int i = 0; i < nums.size(); ++i)
        {
            auto it = s.lower_bound((long) nums[i] - t);
            if (it != s.end() && *it <= (long) nums[i] + t) return true;
            s.insert((long) nums[i]);
            if (i >= k) s.erase((long) nums[i - k]);
        }
        return false;
    }
};
```

### **Go**

```go
import "math/rand"

type node struct {
	ch       [2]*node
	priority int
	val      int
}

func (o *node) cmp(b int) int {
	switch {
	case b < o.val:
		return 0
	case b > o.val:
		return 1
	default:
		return -1
	}
}

func (o *node) rotate(d int) *node {
	x := o.ch[d^1]
	o.ch[d^1] = x.ch[d]
	x.ch[d] = o
	return x
}

type treap struct {
	root *node
}

func (t *treap) _put(o *node, val int) *node {
	if o == nil {
		return &node{priority: rand.Int(), val: val}
	}
	d := o.cmp(val)
	o.ch[d] = t._put(o.ch[d], val)
	if o.ch[d].priority > o.priority {
		o = o.rotate(d ^ 1)
	}
	return o
}

func (t *treap) put(val int) {
	t.root = t._put(t.root, val)
}

func (t *treap) _delete(o *node, val int) *node {
	if d := o.cmp(val); d >= 0 {
		o.ch[d] = t._delete(o.ch[d], val)
		return o
	}
	if o.ch[1] == nil {
		return o.ch[0]
	}
	if o.ch[0] == nil {
		return o.ch[1]
	}
	d := 0
	if o.ch[0].priority > o.ch[1].priority {
		d = 1
	}
	o = o.rotate(d)
	o.ch[d] = t._delete(o.ch[d], val)
	return o
}

func (t *treap) delete(val int) {
	t.root = t._delete(t.root, val)
}

func (t *treap) lowerBound(val int) (lb *node) {
	for o := t.root; o != nil; {
		switch c := o.cmp(val); {
		case c == 0:
			lb = o
			o = o.ch[0]
		case c > 0:
			o = o.ch[1]
		default:
			return o
		}
	}
	return
}

func containsNearbyAlmostDuplicate(nums []int, k, t int) bool {
	s := &treap{}
	for i, num := range nums {
		if lb := s.lowerBound(num - t); lb != nil && lb.val <= num+t {
			return true
		}
		s.put(num)
		if i >= k {
			s.delete(nums[i-k])
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
