# [846. 一手顺子](https://leetcode-cn.com/problems/hand-of-straights)

[English Version](/solution/0800-0899/0846.Hand%20of%20Straights/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>爱丽丝有一手（<code>hand</code>）由整数数组给定的牌。 </p>

<p>现在她想把牌重新排列成组，使得每个组的大小都是 <code>W</code>，且由 <code>W</code> 张连续的牌组成。</p>

<p>如果她可以完成分组就返回 <code>true</code>，否则返回 <code>false</code>。</p>

<p> </p>

<p><strong>注意：</strong>此题目与 1296 重复：<a href="https://leetcode-cn.com/problems/divide-array-in-sets-of-k-consecutive-numbers/" target="_blank">https://leetcode-cn.com/problems/divide-array-in-sets-of-k-consecutive-numbers/</a></p>

<p> </p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>hand = [1,2,3,6,2,3,4,7,8], W = 3
<strong>输出：</strong>true
<strong>解释：</strong>爱丽丝的手牌可以被重新排列为 <code>[1,2,3]，[2,3,4]，[6,7,8]</code>。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>hand = [1,2,3,4,5], W = 4
<strong>输出：</strong>false
<strong>解释：</strong>爱丽丝的手牌无法被重新排列成几个大小为 4 的组。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= hand.length <= 10000</code></li>
	<li><code>0 <= hand[i] <= 10^9</code></li>
	<li><code>1 <= W <= hand.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

TreeMap 实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
from sortedcontainers import SortedDict


class Solution:
    def isNStraightHand(self, hand: List[int], groupSize: int) -> bool:
        if len(hand) % groupSize != 0:
            return False
        sd = SortedDict()
        for h in hand:
            if h in sd:
                sd[h] += 1
            else:
                sd[h] = 1
        while sd:
            v = sd.peekitem(0)[0]
            for i in range(v, v + groupSize):
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
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int h : hand) {
            tm.put(h, tm.getOrDefault(h, 0) + 1);
        }
        while (!tm.isEmpty()) {
            int v = tm.firstKey();
            for (int i = v; i < v + groupSize; ++i) {
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
    bool isNStraightHand(vector<int>& hand, int groupSize) {
        if (hand.size() % groupSize != 0) return false;
        map<int, int> mp;
        for (int& h : hand) mp[h] += 1;
        while (!mp.empty())
        {
            int v = mp.begin()->first;
            for (int i = v; i < v + groupSize; ++i)
            {
                if (!mp.count(i)) return false;
                if (mp[i] == 1) mp.erase(i);
                else mp[i] -= 1;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func isNStraightHand(hand []int, groupSize int) bool {
	if len(hand)%groupSize != 0 {
		return false
	}
	m := treemap.NewWithIntComparator()
	for _, h := range hand {
		if v, ok := m.Get(h); ok {
			m.Put(h, v.(int)+1)
		} else {
			m.Put(h, 1)
		}
	}
	for !m.Empty() {
		v, _ := m.Min()
		for i := v.(int); i < v.(int)+groupSize; i++ {
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
