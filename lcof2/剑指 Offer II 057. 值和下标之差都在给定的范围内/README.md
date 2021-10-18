# [剑指 Offer II 057. 值和下标之差都在给定的范围内](https://leetcode-cn.com/problems/7WqeDu)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和两个整数&nbsp;<code>k</code> 和 <code>t</code> 。请你判断是否存在 <b>两个不同下标</b> <code>i</code> 和 <code>j</code>，使得&nbsp;<code>abs(nums[i] - nums[j]) &lt;= t</code> ，同时又满足 <code>abs(i - j) &lt;= k</code><em> </em>。</p>

<p>如果存在则返回 <code>true</code>，不存在返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,1], k<em> </em>= 3, t = 0
<strong>输出：</strong>true</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,0,1,1], k<em> </em>=<em> </em>1, t = 2
<strong>输出：</strong>true</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,9,1,5,9], k = 2, t = 3
<strong>输出：</strong>false</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= t &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 220&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/contains-duplicate-iii/">https://leetcode-cn.com/problems/contains-duplicate-iii/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“滑动窗口 + 有序集合”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
