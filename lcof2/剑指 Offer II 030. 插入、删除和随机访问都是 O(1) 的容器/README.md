# [剑指 Offer II 030. 插入、删除和随机访问都是 O(1) 的容器](https://leetcode.cn/problems/FortPu)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个支持在<em>平均&nbsp;</em>时间复杂度 <strong>O(1)</strong>&nbsp;下，执行以下操作的数据结构：</p>

<ul>
	<li><code>insert(val)</code>：当元素 <code>val</code> 不存在时返回 <code>true</code>&nbsp;，并向集合中插入该项，否则返回 <code>false</code> 。</li>
	<li><code>remove(val)</code>：当元素 <code>val</code> 存在时返回 <code>true</code>&nbsp;，并从集合中移除该项，否则f返回 <code>true</code>&nbsp;。</li>
	<li><code>getRandom</code>：随机返回现有集合中的一项。每个元素应该有&nbsp;<strong>相同的概率&nbsp;</strong>被返回。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 :</strong></p>

<pre>
<strong>输入: </strong>inputs = [&quot;RandomizedSet&quot;, &quot;insert&quot;, &quot;remove&quot;, &quot;insert&quot;, &quot;getRandom&quot;, &quot;remove&quot;, &quot;insert&quot;, &quot;getRandom&quot;]
[[], [1], [2], [2], [], [1], [2], []]
<strong>输出: </strong>[null, true, false, true, 2, true, false, 2]
<strong>解释:
</strong>RandomizedSet randomSet = new RandomizedSet();  // 初始化一个空的集合
randomSet.insert(1); // 向集合中插入 1 ， 返回 true 表示 1 被成功地插入

randomSet.remove(2); // 返回 false，表示集合中不存在 2

randomSet.insert(2); // 向集合中插入 2 返回 true ，集合现在包含 [1,2]

randomSet.getRandom(); // getRandom 应随机返回 1 或 2

randomSet.remove(1); // 从集合中移除 1 返回 true 。集合现在包含 [2]

randomSet.insert(2); // 2 已在集合中，所以返回 false

randomSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong><meta charset="UTF-8" /></p>

<ul>
	<li><code>-2<sup>31</sup>&nbsp;&lt;= val &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
	<li>最多进行<code> 2 * 10<sup>5</sup></code> 次&nbsp;<code>insert</code> ， <code>remove</code> 和 <code>getRandom</code> 方法调用</li>
	<li>当调用&nbsp;<code>getRandom</code> 方法时，集合中至少有一个元素</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 380&nbsp;题相同：<a href="https://leetcode.cn/problems/insert-delete-getrandom-o1/">https://leetcode.cn/problems/insert-delete-getrandom-o1/</a></p>

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
        self.a = []
        self.m = {}

    def insert(self, val: int) -> bool:
        """
        Inserts a value to the set. Returns true if the set did not already contain the specified element.
        """
        if val in self.m:
            return False
        self.m[val] = len(self.a)
        self.a.append(val)
        return True

    def remove(self, val: int) -> bool:
        """
        Removes a value from the set. Returns true if the set contained the specified element.
        """
        if val in self.m:
            idx = self.m[val]
            self.a[idx], self.a[-1] = self.a[-1], self.a[idx]
            self.m[self.a[idx]] = idx
            self.a.pop()
            del self.m[val]
            return True
        return False

    def getRandom(self) -> int:
        """
        Get a random element from the set.
        """
        return random.choice(self.a)


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
    private final Map<Integer, Integer> m;
    private final List<Integer> a;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        this.m = new HashMap<>();
        this.a = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (this.m.containsKey(val)) {
            return false;
        }
        this.m.put(val, this.a.size());
        this.a.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (this.m.containsKey(val)) {
            int idx = this.m.get(val), last = this.a.size() - 1;
            Collections.swap(this.a, idx, last);
            this.m.put(this.a.get(idx), idx);
            this.a.remove(last);
            this.m.remove(val);
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return this.a.get(ThreadLocalRandom.current().nextInt(this.a.size()));
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

### **C++**

1. 插入

每次添加新数值时，先使用哈希表判断该数值是否存在，存在则直接返回 false。不存在则进行插入操作，只要将该数值添加到数组尾部即可，并将该数值和其下标的映射存入哈希表。

2. 删除

删除同样需使用哈希表判断是否存在，若不存在则返回 false。存在则进行删除操作，在哈希表中删除时间复杂度为 O(1)，但是在数值中删除比较麻烦。若只是直接删除，则为了保证数组内存连续性需将删除数值后面的数值均前移一位，时间复杂度为 O(n)。比较好的处理方式是，用数组的最后一个数值去填充需要删除的数值的内存，其他数值在数组中的位置保持不变，并将这个拿来填充的数值的下标更新即可，最后只要删除数组最后一个数值，同样可以保证时间复杂度为 O(1)。

3. 随机返回

只要随机生成数组下标范围内一个随机下标值，返回该数组下标内的数值即可。

```cpp
class RandomizedSet {
    unordered_map<int, int> mp;
    vector<int> nums;

public:
    RandomizedSet() {
    }

    bool insert(int val) {
        if (mp.count(val))
            return false;

        mp[val] = nums.size();
        nums.push_back(val);
        return true;
    }

    bool remove(int val) {
        if (!mp.count(val))
            return false;

        int removeIndex = mp[val];
        nums[removeIndex] = nums.back();
        mp[nums.back()] = removeIndex;

        mp.erase(val);
        nums.pop_back();
        return true;
    }

    int getRandom() {
        return nums[rand() % nums.size()];
    }
};

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet* obj = new RandomizedSet();
 * bool param_1 = obj->insert(val);
 * bool param_2 = obj->remove(val);
 * int param_3 = obj->getRandom();
 */
```

### **...**

```

```

<!-- tabs:end -->
