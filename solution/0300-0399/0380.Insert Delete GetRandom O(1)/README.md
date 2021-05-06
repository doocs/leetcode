# [380. 常数时间插入、删除和获取随机元素](https://leetcode-cn.com/problems/insert-delete-getrandom-o1)

[English Version](/solution/0300-0399/0380.Insert%20Delete%20GetRandom%20O%281%29/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个支持在<em>平均&nbsp;</em>时间复杂度 <strong>O(1)</strong>&nbsp;下，执行以下操作的数据结构。</p>

<ol>
	<li><code>insert(val)</code>：当元素 val 不存在时，向集合中插入该项。</li>
	<li><code>remove(val)</code>：元素 val 存在时，从集合中移除该项。</li>
	<li><code>getRandom</code>：随机返回现有集合中的一项。每个元素应该有<strong>相同的概率</strong>被返回。</li>
</ol>

<p><strong>示例 :</strong></p>

<pre>
// 初始化一个空的集合。
RandomizedSet randomSet = new RandomizedSet();

// 向集合中插入 1 。返回 true 表示 1 被成功地插入。
randomSet.insert(1);

// 返回 false ，表示集合中不存在 2 。
randomSet.remove(2);

// 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
randomSet.insert(2);

// getRandom 应随机返回 1 或 2 。
randomSet.getRandom();

// 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
randomSet.remove(1);

// 2 已在集合中，所以返回 false 。
randomSet.insert(2);

// 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
randomSet.getRandom();
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“哈希表 + 动态列表”实现。

哈希表存放每个元素的值和对应的下标，而动态列表在每个下标位置存放每个元素。由动态列表实现元素的随机返回。

注意，在 `remove()` 实现上，将列表的最后一个元素设置到待删元素的位置上，然后删除最后一个元素，这样在删除元素的时候，不需要挪动一大批元素，从而实现 `O(1)` 时间内操作。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class RandomizedSet:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.m = {}
        self.l = []

    def insert(self, val: int) -> bool:
        """
        Inserts a value to the set. Returns true if the set did not already contain the specified element.
        """
        if val in self.m:
            return False
        self.m[val] = len(self.l)
        self.l.append(val)
        return True

    def remove(self, val: int) -> bool:
        """
        Removes a value from the set. Returns true if the set contained the specified element.
        """
        if val not in self.m:
            return False
        idx = self.m[val]
        last_idx = len(self.l) - 1
        self.m[self.l[last_idx]] = idx
        self.m.pop(val)
        self.l[idx] = self.l[last_idx]
        self.l.pop()
        return True

    def getRandom(self) -> int:
        """
        Get a random element from the set.
        """
        return random.choice(self.l)


# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class RandomizedSet {
    private Map<Integer, Integer> m;
    private List<Integer> l;
    private Random rnd;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        m = new HashMap<>();
        l = new ArrayList<>();
        rnd = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (m.containsKey(val)) {
            return false;
        }
        m.put(val, l.size());
        l.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!m.containsKey(val)) {
            return false;
        }
        int idx = m.get(val);
        int lastIdx = l.size() - 1;
        m.put(l.get(lastIdx), idx);
        m.remove(val);
        l.set(idx, l.get(lastIdx));
        l.remove(lastIdx);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int idx = rnd.nextInt(l.size());
        return l.get(idx);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
```

### **...**

```

```

<!-- tabs:end -->
