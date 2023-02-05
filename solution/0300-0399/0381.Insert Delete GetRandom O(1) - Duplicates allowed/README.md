# [381. O(1) 时间插入、删除和获取随机元素 - 允许重复](https://leetcode.cn/problems/insert-delete-getrandom-o1-duplicates-allowed)

[English Version](/solution/0300-0399/0381.Insert%20Delete%20GetRandom%20O%281%29%20-%20Duplicates%20allowed/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>RandomizedCollection</code> 是一种包含数字集合(可能是重复的)的数据结构。它应该支持插入和删除特定元素，以及删除随机元素。</p>

<p>实现 <code>RandomizedCollection</code> 类:</p>

<ul>
	<li><code>RandomizedCollection()</code>初始化空的 <code>RandomizedCollection</code> 对象。</li>
	<li><code>bool insert(int val)</code>&nbsp;将一个 <code>val</code> 项插入到集合中，即使该项已经存在。如果该项不存在，则返回 <code>true</code> ，否则返回 <code>false</code> 。</li>
	<li><code>bool remove(int val)</code>&nbsp;如果存在，从集合中移除一个 <code>val</code> 项。如果该项存在，则返回 <code>true</code> ，否则返回 <code>false</code> 。注意，如果 <code>val</code> 在集合中出现多次，我们只删除其中一个。</li>
	<li><code>int getRandom()</code> 从当前的多个元素集合中返回一个随机元素。每个元素被返回的概率与集合中包含的相同值的数量 <strong>线性相关</strong> 。</li>
</ul>

<p>您必须实现类的函数，使每个函数的 <strong>平均</strong> 时间复杂度为 <code>O(1)</code> 。</p>

<p><strong>注意：</strong>生成测试用例时，只有在 <code>RandomizedCollection</code> 中 <strong>至少有一项</strong> 时，才会调用 <code>getRandom</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入</strong>
["RandomizedCollection", "insert", "insert", "insert", "getRandom", "remove", "getRandom"]
[[], [1], [1], [2], [], [1], []]
<strong>输出</strong>
[null, true, false, true, 2, true, 1]

<strong>解释</strong>
RandomizedCollection collection = new RandomizedCollection();// 初始化一个空的集合。
collection.insert(1);   // 返回 true，因为集合不包含 1。
                        // 将 1 插入到集合中。
collection.insert(1);   // 返回 false，因为集合包含 1。
&nbsp;                       // 将另一个 1 插入到集合中。集合现在包含 [1,1]。
collection.insert(2);   // 返回 true，因为集合不包含 2。
&nbsp;                       // 将 2 插入到集合中。集合现在包含 [1,1,2]。
collection.getRandom(); // getRandom 应当:
&nbsp;                       // 有 2/3 的概率返回 1,
&nbsp;                       // 1/3 的概率返回 2。
collection.remove(1);   // 返回 true，因为集合包含 1。
&nbsp;                       // 从集合中移除 1。集合现在包含 [1,2]。
collection.getRandom(); // getRandom 应该返回 1 或 2，两者的可能性相同。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>-2<sup>31</sup>&nbsp;&lt;= val &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
	<li><code>insert</code>,&nbsp;<code>remove</code>&nbsp;和&nbsp;<code>getRandom</code>&nbsp;最多 <strong>总共</strong> 被调用&nbsp;<code>2 * 10<sup>5</sup></code>&nbsp;次</li>
	<li>当调用 <code>getRandom</code> 时，数据结构中 <strong>至少有一个</strong> 元素</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“哈希表 + 动态列表”实现。

哈希表存放每个元素的值和对应的下标集合，而动态列表在每个下标位置存放每个元素。由动态列表实现元素的随机返回。

注意，在 `remove()` 实现上，将列表的最后一个元素设置到待删元素的位置上，然后删除最后一个元素，这样在删除元素的时候，不需要挪动一大批元素，从而实现 `O(1)` 时间内操作。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class RandomizedCollection:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.m = {}
        self.l = []

    def insert(self, val: int) -> bool:
        """
        Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
        """
        idx_set = self.m.get(val, set())
        idx_set.add(len(self.l))
        self.m[val] = idx_set
        self.l.append(val)
        return len(idx_set) == 1

    def remove(self, val: int) -> bool:
        """
        Removes a value from the collection. Returns true if the collection contained the specified element.
        """
        if val not in self.m:
            return False
        idx_set = self.m[val]
        idx = list(idx_set)[0]
        last_idx = len(self.l) - 1
        self.l[idx] = self.l[last_idx]
        idx_set.remove(idx)

        last_idx_set = self.m[self.l[last_idx]]
        if last_idx in last_idx_set:
            last_idx_set.remove(last_idx)
        if idx < last_idx:
            last_idx_set.add(idx)
        if not idx_set:
            self.m.pop(val)
        self.l.pop()
        return True

    def getRandom(self) -> int:
        """
        Get a random element from the collection.
        """
        return -1 if len(self.l) == 0 else random.choice(self.l)


# Your RandomizedCollection object will be instantiated and called as such:
# obj = RandomizedCollection()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class RandomizedCollection {
    private Map<Integer, Set<Integer>> m;
    private List<Integer> l;
    private Random rnd;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        m = new HashMap<>();
        l = new ArrayList<>();
        rnd = new Random();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain
     * the specified element.
     */
    public boolean insert(int val) {
        m.computeIfAbsent(val, k -> new HashSet<>()).add(l.size());
        l.add(val);
        return m.get(val).size() == 1;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified
     * element.
     */
    public boolean remove(int val) {
        if (!m.containsKey(val)) {
            return false;
        }
        Set<Integer> idxSet = m.get(val);
        int idx = idxSet.iterator().next();
        int lastIdx = l.size() - 1;
        l.set(idx, l.get(lastIdx));
        idxSet.remove(idx);

        Set<Integer> lastIdxSet = m.get(l.get(lastIdx));
        lastIdxSet.remove(lastIdx);
        if (idx < lastIdx) {
            lastIdxSet.add(idx);
        }
        if (idxSet.isEmpty()) {
            m.remove(val);
        }
        l.remove(lastIdx);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        int size = l.size();
        return size == 0 ? -1 : l.get(rnd.nextInt(size));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
```

### **...**

```

```

<!-- tabs:end -->
